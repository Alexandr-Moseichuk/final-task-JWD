package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.dao.Transaction;

public class BaseService {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
