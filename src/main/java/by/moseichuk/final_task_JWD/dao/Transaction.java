package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;

public interface Transaction {

    <Type extends Dao<?>> Type getDao(Class<Type> daoClass) throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
