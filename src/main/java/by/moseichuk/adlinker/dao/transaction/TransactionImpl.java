package by.moseichuk.adlinker.dao.transaction;

import by.moseichuk.adlinker.dao.*;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.impl.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TransactionImpl implements Transaction {
    private static Map<DaoEnum, BaseDao> daoMap = new HashMap<>();

    static {
        daoMap.put(DaoEnum.CAMPAIGN, new CampaignDaoImpl());
        daoMap.put(DaoEnum.MANAGER_INFLUENCER, new ManagerInfluencerDaoImpl());
        daoMap.put(DaoEnum.APPLICATION, new ApplicationDaoImpl());
        daoMap.put(DaoEnum.SOCIAL_LINK, new SocialLinkDaoImpl());
        daoMap.put(DaoEnum.USER, new UserDaoImpl());
        daoMap.put(DaoEnum.FILE, new UserFileDaoImpl());
        daoMap.put(DaoEnum.USER_INFO, new UserInfoDaoImpl());
    }

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BaseDao getDao(DaoEnum daoType) {
        BaseDao baseDao = daoMap.get(daoType);
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
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }
}
