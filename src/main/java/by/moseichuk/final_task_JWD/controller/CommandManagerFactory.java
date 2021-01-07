package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.final_task_JWD.service.ServiceFactory;
import by.moseichuk.final_task_JWD.service.impl.ServiceFactoryImpl;

public class CommandManagerFactory {
    private static final CommandManagerFactory instance = new CommandManagerFactory();

    private CommandManagerFactory(){}

    public static CommandManagerFactory getInstance() {
        return instance;
    }

    //TODO перепрыгиваем сорвисы и обращаемся к DAO, исправить
    public CommandManger getManager() throws TransactionException {
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        ServiceFactory serviceFactory = new ServiceFactoryImpl(transactionFactory);
        return new CommandManagerImpl(serviceFactory);
    }

}
