package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.impl.BaseDao;

public interface Transaction {

    <Type extends BaseDao> Type getDao(String daoInterface) throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
