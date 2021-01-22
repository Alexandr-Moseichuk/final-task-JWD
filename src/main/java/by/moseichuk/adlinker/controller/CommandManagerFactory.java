package by.moseichuk.adlinker.controller;

import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.impl.ServiceFactoryImpl;

public class CommandManagerFactory {
    private static final CommandManagerFactory instance = new CommandManagerFactory();

    private CommandManagerFactory(){}

    public static CommandManagerFactory getInstance() {
        return instance;
    }

    public CommandManger getManager() throws TransactionException {
        return new CommandManagerImpl(new ServiceFactoryImpl());
    }

}
