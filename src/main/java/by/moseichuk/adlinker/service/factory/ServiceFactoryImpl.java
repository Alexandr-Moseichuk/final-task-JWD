package by.moseichuk.adlinker.service.factory;

import by.moseichuk.adlinker.dao.TransactionFactory;
import by.moseichuk.adlinker.dao.exception.ConnectionPoolException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.pool.ConnectionPool;
import by.moseichuk.adlinker.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.ServiceFactory;
import by.moseichuk.adlinker.service.impl.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ServiceFactoryImpl implements ServiceFactory {
    private final TransactionFactory transactionFactory;

    private static final String PROPERTIES_FILE = "db_config.properties";

    public ServiceFactoryImpl() throws TransactionException {
        transactionFactory = new TransactionFactoryImpl();
    }

    public static void init() throws ConnectionPoolException, IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + PROPERTIES_FILE;
        Properties props = new Properties();
        props.load(new FileInputStream(appConfigPath));

        ConnectionPool.getInstance().init(
                props.getProperty("db.class"),
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password"),
                Integer.parseInt(props.getProperty("pool.start_size")),
                Integer.parseInt(props.getProperty("pool.size")),
                Integer.parseInt(props.getProperty("pool.connection_timeout")));
    }

    public static void destroy() {
        ConnectionPool.getInstance().destroy();
    }

    @Override
    public BaseService getService(ServiceEnum serviceType) {
        BaseService service;
        switch (serviceType) {
            case CAMPAIGN:
                service = new CampaignServiceImpl();
                break;
            case USER:
                service = new UserServiceImpl();
                break;
            case APPLICATION:
                service = new ApplicationServiceImpl();
                break;
            case MANAGER_INFLUENCER:
                service = new ManagerInfluencerServiceImpl();
                break;
            case USER_INFO:
                service = new UserInfoServiceImpl();
                break;
            case MANAGER:
                service = new ManagerServiceImpl();
                break;
            case INFLUENCER:
                service = new InfluencerServiceImpl();
                break;
            case FILE:
                service = new UserFileServiceImpl();
                break;
            default:
                throw new IllegalArgumentException("Illegal service type");
        }
        service.setTransaction(transactionFactory.createTransaction());
        return service;
    }

    @Override
    public void close() {
        transactionFactory.close();
    }

}
