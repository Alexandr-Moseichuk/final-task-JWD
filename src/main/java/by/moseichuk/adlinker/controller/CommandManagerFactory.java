package by.moseichuk.adlinker.controller;

import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.ServiceFactory;
import by.moseichuk.adlinker.service.impl.ServiceFactoryImpl;

public class CommandManagerFactory {
    private static final CommandManagerFactory instance = new CommandManagerFactory();

    private CommandManagerFactory(){}

    public static CommandManagerFactory getInstance() {
        return instance;
    }

    public CommandManger getManager() throws TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        return new CommandManagerImpl(serviceFactory);
    }

}
