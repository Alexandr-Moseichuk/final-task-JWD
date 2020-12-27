package by.moseichuk.final_task_JWD.dao.transaction;

import by.moseichuk.final_task_JWD.dao.Transaction;
import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public TransactionFactoryImpl() throws TransactionException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            //TODO
            //connection.setAutoCommit(false);
        } catch (ConnectionPoolException e) {
            throw new TransactionException(e);
        }
//        catch (SQLException e) {
//            throw new TransactionException("Can't disable autocommit", e);
//        }

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
