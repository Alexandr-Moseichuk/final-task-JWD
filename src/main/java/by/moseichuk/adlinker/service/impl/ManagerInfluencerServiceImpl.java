package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.ManagerInfluencerDao;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.ManagerInfluencerService;
import by.moseichuk.adlinker.service.exception.ServiceException;

public class ManagerInfluencerServiceImpl extends BaseService implements ManagerInfluencerService {
    @Override
    public void create(Influencer influencer) throws ServiceException {
        ManagerInfluencerDao dao = (ManagerInfluencerDao) transaction.getDao(DaoEnum.MANAGER_INFLUENCER);
        try {
            dao.create(influencer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
