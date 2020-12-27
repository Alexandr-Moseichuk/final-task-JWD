package by.moseichuk.final_task_JWD.dao.pool;

import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final ConnectionPool instance = new ConnectionPool();

    private String url;
    private String username;
    private String password;
    private int poolSize;
    private int connectionTimeout;

    private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {}



    public static ConnectionPool getInstance() {
        return instance;
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
        PooledConnection connection = null;
       // while(connection == null) {
            try {
                if(!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if(!connection.isValid(connectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch(SQLException e) {}
                        connection = null;
                    }
                } else if(usedConnections.size() < poolSize) {
                    connection = createConnection();
                } else {
                    throw new ConnectionPoolException("The limit of number of database connections is exceeded");
                }
            } catch(InterruptedException | SQLException e) {
                throw new ConnectionPoolException("Impossible to connect to a database",e);
            }
        //}
        usedConnections.add(connection);
        LOGGER.debug(String.format("Connection was created. Pool state: %s taken connections; %s free connections", usedConnections.size(), freeConnections.size()));

        return connection;
    }

    synchronized void freeConnection(PooledConnection connection) {
        try {
            if(connection.isValid(connectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                LOGGER.debug(String.format("Connection was returned into pool. Pool state: %s taken connections; %s free connections", usedConnections.size(), freeConnections.size()));
            }
        } catch(SQLException | InterruptedException e1) {
            try {
                connection.getConnection().close();
            } catch(SQLException e2) {}
        }
    }

    public synchronized void init(String driverClass, String url, String username, String password,
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

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, username, password));
    }

    public synchronized void destroy() {
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
