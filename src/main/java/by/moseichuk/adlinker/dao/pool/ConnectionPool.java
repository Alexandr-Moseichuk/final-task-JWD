package by.moseichuk.adlinker.dao.pool;

import by.moseichuk.adlinker.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe pool of database connections.
 *
 * @author Alexandr Moseichuk
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final ConnectionPool instance = new ConnectionPool();
    private static final ReentrantLock lock = new ReentrantLock();

    private String url;
    private String username;
    private String password;
    private int poolSize;
    private int connectionTimeout;

    /**
     * Queue of available connections
     */
    private final BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    /**
     * Set of taken connections
     */
    private final Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    /**
     * Default empty constructor
     */
    private ConnectionPool() {}


    /**
     * Returns the instance of class.
     *
     * @return the instance of class
     */
    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Returns free connection from the pool and puts connection to {@code usedConnections} list.
     * If there is no any free connection waits {@code connectionTimeout} seconds
     *
     * @return free connection from the pool.
     * @throws ConnectionPoolException if there is no free connections or can't create new one
     */
    public Connection getConnection() throws ConnectionPoolException {
        PooledConnection connection = null;
        lock.lock();
        try {
            if (!freeConnections.isEmpty()) {
                connection = freeConnections.take();
                if(!connection.isValid(connectionTimeout)) {
                    try {
                        connection.getConnection().close();
                    } catch(SQLException e) {
                        LOGGER.debug("Can't close connection " + e.getMessage());
                    }
                    connection = null;
                }
            } else if(usedConnections.size() < poolSize) {
                connection = createConnection();
            } else {
                throw new ConnectionPoolException("The limit of number of database connections is exceeded");
            }
            usedConnections.add(connection);
        } catch(InterruptedException | SQLException e) {
            throw new ConnectionPoolException("Impossible to connect to a database", e);
        } finally {
            lock.unlock();
        }

        LOGGER.debug(String.format("Connection was created. Pool state: %s taken connections; %s free connections", usedConnections.size(), freeConnections.size()));

        return connection;
    }

    /**
     * Returns connection to free connection list. And connection can be used again.
     *
     * @param connection that will be in list of free connections
     */
    void freeConnection(PooledConnection connection) {
        try {
            if(connection.isValid(connectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                LOGGER.debug(String.format("Connection was returned into pool. Pool state: %s taken connections; %s free connections", usedConnections.size(), freeConnections.size()));
            } else {
                usedConnections.remove(connection);
                connection.getConnection().close();
                LOGGER.debug("Invalid connection was destroyed while trying to release connection");
            }
        } catch(SQLException | InterruptedException e1) {
            try {
                connection.getConnection().close();
            } catch(SQLException e2) {
                LOGGER.debug("Can't close connection " + e2.getMessage());
            }
        }
    }

    /**
     * Configures a pool with params
     *
     * @param driverClass       class of database driver
     * @param url               database url
     * @param username          username of database user
     * @param password          password of database user
     * @param startSize         number of connections at the start of the pool
     * @param poolSize          max number of connections
     * @param connectionTimeout number of seconds to timeout connection
     * @throws ConnectionPoolException if can't create connection
     */
    public void init(String driverClass, String url, String username, String password,
                                  int startSize, int poolSize, int connectionTimeout) throws ConnectionPoolException {
        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.username = username;
            this.password = password;
            this.poolSize = poolSize;
            this.connectionTimeout = connectionTimeout;
            for(int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
            LOGGER.debug(String.format("Connection pool was created. Driver: %s. URL: %s. Start size: %d. Pool size: %d. Connection timeout: %d.",
                    driverClass, url, startSize, poolSize, connectionTimeout));
        } catch(ClassNotFoundException | SQLException | InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Creates new {@code PooledConnection} to database
     *
     * @return new {@code PoolledConnection}
     * @throws SQLException if can't create connection
     */
    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, username, password));
    }

    /**
     * Destroys connection pool and close all connections
     */
    public void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for(PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch(SQLException e) {}
        }
        usedConnections.clear();
    }

    @Override
    protected void finalize() {
        destroy();
    }
}
