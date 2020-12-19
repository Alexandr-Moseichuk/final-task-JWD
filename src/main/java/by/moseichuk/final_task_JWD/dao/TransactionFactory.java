package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.dao.exception.TransactionException;

public interface TransactionFactory {

    Transaction createTransaction() throws TransactionException;

    void close();

}
