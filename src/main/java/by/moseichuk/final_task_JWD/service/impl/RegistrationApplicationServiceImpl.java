package by.moseichuk.final_task_JWD.service.impl;

import by.moseichuk.final_task_JWD.bean.RegistrationApplication;
import by.moseichuk.final_task_JWD.dao.DaoEnum;
import by.moseichuk.final_task_JWD.dao.RegistrationApplicationDao;
import by.moseichuk.final_task_JWD.dao.UserDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.service.BaseService;
import by.moseichuk.final_task_JWD.service.RegistrationApplicationService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public class RegistrationApplicationServiceImpl extends BaseService implements RegistrationApplicationService {
    @Override
    public List<RegistrationApplication> readAll() throws ServiceException {
        RegistrationApplicationDao regDao = (RegistrationApplicationDao) transaction.getDao(DaoEnum.REGISTRATION_APPLICATION);
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<RegistrationApplication> applicationList = regDao.readAll();
            for (RegistrationApplication application : applicationList) {
                application.setUser(userDao.read(application.getUserId()));
            }
            return applicationList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
