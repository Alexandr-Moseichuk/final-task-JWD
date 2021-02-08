package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserInfo;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.dao.*;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public User login(String mail, String password) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
            UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
            User user = userDao.readByEmail(mail);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                user.setUserInfo(userInfoDao.read(user.getId()));
                return user;
            } else {
                return null;
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void register(User user, UserInfo userInfo, Application application) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            Integer userId = userDao.create(user);
            userInfo.setUserId(userId);
            userInfoDao.create(userInfo);
            application.setUserId(userId);
            applicationDao.create(application);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }

    }

    @Override
    public Integer create(User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
            Integer id = userDao.create(user);
            transaction.commit();
            return id;
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public User read(Integer userId) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            User user = userDao.read(userId);
            UserInfo userInfo = userInfoDao.read(userId);
            user.setUserInfo(userInfo);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
        try {
            userDao.update(user);
            userInfoDao.update(user.getUserInfo());
            userFileDao.update(user.getUserInfo().getUserFile());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> readAll() throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<User> userList = userDao.readAll();
            return userList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> readUsersByRole(UserRole userRole) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            List<User> userList = userDao.readUsersByRole(userRole);

            for (User user : userList) {
                UserInfo userInfo = userInfoDao.read(user.getId());
                user.setUserInfo(userInfo);
            }
            return userList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
