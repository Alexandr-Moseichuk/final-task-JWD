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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
    @Override
    public User login(String mail, String password) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
            return userDao.login(mail, md5(password));
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
    public List<Influencer> readInfluencerList() throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            List<User> userList = userDao.readUsersByRole(UserRole.INFLUENCER);
            List<Influencer> influencerList = new ArrayList<>();
            for (User user : userList) {
                Influencer influencer = new Influencer();
                UserInfo userInfo = userInfoDao.read(user.getId());
                influencer.setId(user.getId());
                influencer.setEmail(user.getEmail());
                influencer.setRole(user.getRole());
                influencer.setStatus(user.getStatus());
                influencer.setRegistrationDate(user.getRegistrationDate());
                influencer.setUserInfo(userInfo);

                influencerList.add(influencer);
            }
            return influencerList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private String md5(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(password.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for(int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5summ = formatter.toString();
            formatter.close();
            return md5summ;
        } catch(NoSuchAlgorithmException e) {
            return null;
        }
    }
}
