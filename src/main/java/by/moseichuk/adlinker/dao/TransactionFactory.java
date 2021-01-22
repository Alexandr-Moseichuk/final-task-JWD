package by.moseichuk.adlinker.dao;

public interface TransactionFactory {

    Transaction createTransaction();

    void close();

}
