package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.impl.BaseDao;

public interface Transaction {

    <Type extends BaseDao> Type getDao(DaoEnum daoType);

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
