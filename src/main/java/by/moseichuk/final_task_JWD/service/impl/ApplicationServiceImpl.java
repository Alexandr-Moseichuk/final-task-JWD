package by.moseichuk.final_task_JWD.service.impl;

import by.moseichuk.final_task_JWD.bean.Application;
import by.moseichuk.final_task_JWD.bean.UserStatus;
import by.moseichuk.final_task_JWD.dao.DaoEnum;
import by.moseichuk.final_task_JWD.dao.ApplicationDao;
import by.moseichuk.final_task_JWD.dao.UserDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.service.BaseService;
import by.moseichuk.final_task_JWD.service.ApplicationService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public class ApplicationServiceImpl extends BaseService implements ApplicationService {

    @Override
    public void add(Application application) throws ServiceException {
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            applicationDao.create(application);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Application application) throws ServiceException {
        ApplicationDao applicationDao = (ApplicationDao) transaction.getDao(DaoEnum.APPLICATION);
        try {
            applicationDao.update(application);
        } catch (DaoException e) {
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
    public void approveByIds(List<Integer> idList) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);

        try {
            userDao.updateStatus(idList, UserStatus.VERIFIED);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void rejectByIds(List<Integer> idList) throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);

        try {
            userDao.updateStatus(idList, UserStatus.ARCHIVE);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
