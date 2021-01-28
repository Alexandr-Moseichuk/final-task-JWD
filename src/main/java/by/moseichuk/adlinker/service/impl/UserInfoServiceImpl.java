package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.UserInfo;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.UserInfoDao;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.UserInfoService;
import by.moseichuk.adlinker.service.exception.ServiceException;

public class UserInfoServiceImpl extends BaseService implements UserInfoService {
    @Override
    public void create(UserInfo userInfo) throws ServiceException {
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            userInfoDao.create(userInfo);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public UserInfo read(Integer userId) throws ServiceException {
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            return userInfoDao.read(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserInfo readManagerByUserId(Integer userId) throws ServiceException {
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            return userInfoDao.read(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
