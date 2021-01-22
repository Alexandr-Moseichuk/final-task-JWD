package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.dao.Transaction;

public class BaseService {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
