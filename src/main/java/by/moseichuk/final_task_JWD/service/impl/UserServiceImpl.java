package by.moseichuk.final_task_JWD.service.impl;

import by.moseichuk.final_task_JWD.bean.Influencer;
import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserInfo;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.dao.DaoEnum;
import by.moseichuk.final_task_JWD.dao.ManagerInfluencerDao;
import by.moseichuk.final_task_JWD.dao.UserDao;
import by.moseichuk.final_task_JWD.dao.UserInfoDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.service.BaseService;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
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
    public Integer create(User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
            return userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createUserInfo(UserInfo userInfo) throws ServiceException {
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            userInfoDao.create(userInfo);
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
