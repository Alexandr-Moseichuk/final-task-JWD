package by.moseichuk.adlinker.controller.manager;

import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.impl.ServiceFactoryImpl;

/**
 * Singleton factory witch produce command manager with service factory.
 *
 * @author Moseichuk Alexadr
 */
public class CommandManagerFactory {
    private static final CommandManagerFactory instance = new CommandManagerFactory();

    private CommandManagerFactory(){}

    /**
     * Returns the instance of factory
     *
     * @return the instance of factory
     */
    public static CommandManagerFactory getInstance() {
        return instance;
    }

    /**
     * Creates new {@code CommandManager} with new {@code ServiceFactoryImpl}
     * 
     * @return new {@code CommandManager} object
     * @throws TransactionException if cant create service factory
     */
    public CommandManger getManager() throws TransactionException {
        return new CommandManagerImpl(new ServiceFactoryImpl());
    }

}
