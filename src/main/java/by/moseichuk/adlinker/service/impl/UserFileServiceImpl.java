package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.UserFile;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.UserFileDao;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.UserFileService;
import by.moseichuk.adlinker.service.exception.ServiceException;

public class UserFileServiceImpl extends BaseService implements UserFileService {

    @Override
    public Integer create(UserFile userFile) throws ServiceException {
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
        try {
            Integer fileId = userFileDao.create(userFile);
            transaction.commit();
            return fileId;
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(UserFile userFile) throws ServiceException {
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
        try {
            userFileDao.update(userFile);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

}
