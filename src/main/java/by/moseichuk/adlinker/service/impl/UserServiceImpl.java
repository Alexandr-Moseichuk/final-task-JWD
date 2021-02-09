package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserInfo;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.constant.UserStatus;
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
            UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
            User user = userDao.readByEmail(mail);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                buildUser(user);
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
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
        try {
            Integer userId = userDao.create(user);
            Integer photoId = userFileDao.create(userInfo.getUserFile());
            userInfo.setUserId(userId);
            userInfo.getUserFile().setId(photoId);
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

        try {
            User user = userDao.read(userId);
            buildUser(user);
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
    public void delete(Integer userId) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        SocialLinkDao socialLinkDao = (SocialLinkDao) transaction.getDao(DaoEnum.SOCIAL_LINK);
        UserCampaignDao userCampaignDao = (UserCampaignDao) transaction.getDao(DaoEnum.USER_CAMPAIGN);
        ManagerInfluencerDao managerInfluencerDao = (ManagerInfluencerDao) transaction.getDao(DaoEnum.MANAGER_INFLUENCER);
        try {
            UserInfo userInfo = userInfoDao.read(userId);
            socialLinkDao.delete(userId);
            applicationDao.delete(userId);
            userInfoDao.delete(userId);
            userFileDao.delete(userInfo.getUserFile().getId());
            userCampaignDao.unsubscribeAll(userId);
            managerInfluencerDao.delete(userId);
            userDao.delete(userId);

            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> readAll() throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<User> userList = userDao.readAll();
            for (User user : userList) {
                buildUser(user);
            }
            return userList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> readUsersByRole(UserRole userRole) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<User> userList = userDao.readUsersByRole(userRole);

            for (User user : userList) {
                buildUser(user);
            }
            return userList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateStatus(List<Integer> userIds, UserStatus userStatus) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            userDao.updateStatus(userIds, userStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildUser(User user) throws DaoException {
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);

        UserInfo userInfo = userInfoDao.read(user.getId());
        userInfo.setUserFile(userFileDao.read(userInfo.getUserFile().getId()));
        user.setUserInfo(userInfo);
    }
}
