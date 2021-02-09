package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.ApplicationDao;
import by.moseichuk.adlinker.dao.UserDao;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.ApplicationService;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public class ApplicationServiceImpl extends BaseService implements ApplicationService {

    @Override
    public void add(Application application) throws ServiceException {
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            applicationDao.create(application);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public Application read(Integer userId) throws ServiceException {
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            return applicationDao.read(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Application application) throws ServiceException {
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            applicationDao.update(application);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Application> readAll() throws ServiceException {
        ApplicationDao regDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<Application> applicationList = regDao.readAll();
            for (Application application : applicationList) {
                application.setUser(userDao.read(application.getUserId()));
            }
            return applicationList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Application> readUnverifiedSubList(int count, int offset) throws ServiceException {
        ApplicationDao appDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<Application> subList = appDao.readUnverifiedSubList(count, offset);
            for (Application application : subList) {
                application.setUser(userDao.read(application.getUserId()));
            }
            return subList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int readRowCount() throws ServiceException {
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            return applicationDao.readRowCount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void approveByIds(List<Integer> idList) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            userDao.updateStatus(idList, UserStatus.VERIFIED);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public void rejectByIds(List<Integer> idList) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            userDao.updateStatus(idList, UserStatus.ARCHIVE);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }
}
