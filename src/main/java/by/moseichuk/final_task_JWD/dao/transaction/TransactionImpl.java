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
    private static Map<Class<? extends Dao<?>>, Class<? extends BaseDao>> daoMap = new HashMap<>();
    static {
        daoMap.put(CampaignDao.class, CampaignDaoImpl.class);
        daoMap.put(ManagerInfluencerDao.class, ManagerInfluencerDaoImpl.class);
        daoMap.put(RegistrationApplicationDao.class, RegistrationApplicationDaoImpl.class);
        daoMap.put(SocialLinkDao.class, SocialLinkDaoImpl.class);
        daoMap.put(UserDao.class, UserDaoImpl.class);
        daoMap.put(UserFileDao.class, UserFileDaoImpl.class);
        daoMap.put(UserInfoDao.class, UserInfoDaoImpl.class);
    }

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type getDao(Class<Type> daoInterface) throws TransactionException {
        Class<? extends BaseDao> daoClass = daoMap.get(daoInterface);
        if (daoClass != null) {
            try {
                BaseDao baseDao = daoClass.newInstance();
                baseDao.setConnection(connection);
                return (Type) baseDao;
            } catch (InstantiationException | IllegalAccessException e) {
                throw new TransactionException("Can't create DAO object");
            }
        } else {
            throw new TransactionException("No such DAO interface");
        }
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
