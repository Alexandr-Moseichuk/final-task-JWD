package by.moseichuk.adlinker.controller;

import by.moseichuk.adlinker.dao.TransactionFactory;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.adlinker.service.ServiceFactory;
import by.moseichuk.adlinker.service.impl.ServiceFactoryImpl;

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
