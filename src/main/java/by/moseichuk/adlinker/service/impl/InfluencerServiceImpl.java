package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.ManagerInfluencerDao;
import by.moseichuk.adlinker.dao.UserInfoDao;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.InfluencerService;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public class InfluencerServiceImpl extends BaseService implements InfluencerService {
    @Override
    public List<Influencer> readByManagerId(Integer managerId) throws ServiceException {
        ManagerInfluencerDao dao = (ManagerInfluencerDao) transaction.getDao(DaoEnum.MANAGER_INFLUENCER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        try {
            List<Influencer> influencerList = dao.readManagerInfluencers(managerId);
            for (Influencer influencer : influencerList) {
                influencer.setUserInfo(userInfoDao.read(influencer.getId()));
            }
            return influencerList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
