package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.ManagerInfluencerDao;
import by.moseichuk.adlinker.dao.UserInfoDao;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.ManagerService;
import by.moseichuk.adlinker.service.exception.ServiceException;

public class ManagerServiceImpl extends BaseService implements ManagerService {
    @Override
    public Manager readByInfluencerId(Integer influencerId) throws ServiceException {
        ManagerInfluencerDao managerInfluencerDao =
                (ManagerInfluencerDao) transaction.getDao(DaoEnum.MANAGER_INFLUENCER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            Manager manager = managerInfluencerDao.readManager(influencerId);
            if (manager != null) {
                manager.setUserInfo(userInfoDao.read(manager.getId()));
            }
            return manager;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
