package by.moseichuk.final_task_JWD.dao.transaction;

import by.moseichuk.final_task_JWD.dao.*;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.impl.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TransactionImpl implements Transaction {

    private static Map<String, ? super Dao<?>> daoMap = new HashMap<>();
    static {
        daoMap.put("CampaignDao", new CampaignDaoImpl());
        daoMap.put("ManagerInfluencerDao", new ManagerInfluencerDaoImpl());
        daoMap.put("RegistrationApplicationDao", new RegistrationApplicationDaoImpl());
        daoMap.put("SocialLinkDao", new SocialLinkDaoImpl());
        daoMap.put("UserDao", new UserDaoImpl());
        daoMap.put("UserFileDao", new UserFileDaoImpl());
        daoMap.put("UserInfoDao", new UserInfoDaoImpl());
    }

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends BaseDao> Type getDao(String daoInterface) {
        Type dao = (Type) daoMap.get(daoInterface);
        dao.setConnection(connection);
        return dao;
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
