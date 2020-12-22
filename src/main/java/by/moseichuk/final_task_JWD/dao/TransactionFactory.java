package by.moseichuk.final_task_JWD.dao;

public interface TransactionFactory {

    Transaction createTransaction();

    void close();

}
