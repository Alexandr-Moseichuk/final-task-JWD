package by.moseichuk.adlinker.dao.transaction;

import by.moseichuk.adlinker.dao.*;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TransactionImpl implements Transaction {
    private static final Logger LOGGER = LogManager.getLogger(TransactionImpl.class);

    private final Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BaseDao getDao(DaoEnum daoType) {
        BaseDao baseDao;
        switch (daoType) {
            case CAMPAIGN:
                baseDao = new CampaignDaoImpl();
                break;
            case MANAGER_INFLUENCER:
                baseDao = new ManagerInfluencerDaoImpl();
                break;
            case APPLICATION:
                baseDao = new ApplicationDaoImpl();
                break;
            case SOCIAL_LINK:
                baseDao = new SocialLinkDaoImpl();
                break;
            case USER:
                baseDao = new UserDaoImpl();
                break;
            case FILE:
                baseDao = new UserFileDaoImpl();
                break;
            case USER_INFO:
                baseDao = new UserInfoDaoImpl();
                break;
            case USER_CAMPAIGN:
                baseDao = new UserCampaignDaoImpl();
                break;
            default:
                throw new IllegalArgumentException("Illegal DAO type");
        }
        baseDao.setConnection(connection);
        return baseDao;
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
