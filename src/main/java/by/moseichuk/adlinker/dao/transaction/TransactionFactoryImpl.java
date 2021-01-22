package by.moseichuk.adlinker.dao.transaction;

import by.moseichuk.adlinker.dao.Transaction;
import by.moseichuk.adlinker.dao.TransactionFactory;
import by.moseichuk.adlinker.dao.exception.ConnectionPoolException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private final Connection connection;

    public TransactionFactoryImpl() throws TransactionException {
        try {
            connection = ConnectionPool.getInstance().getConnection();

            connection.setAutoCommit(false);
        } catch (ConnectionPoolException e) {
            throw new TransactionException(e);
        }
        catch (SQLException e) {
            throw new TransactionException("Can't disable autocommit", e);
        }

    }
    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            //TODO logger
        }
    }
}
