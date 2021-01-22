package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.impl.BaseDao;

public interface Transaction {

    BaseDao getDao(DaoEnum daoType);

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
