package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.SocialLink;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.dao.*;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.InfluencerService;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class InfluencerServiceImpl extends BaseService implements InfluencerService {

    @Override
    public List<Influencer> readByManagerId(Integer managerId) throws ServiceException {
        ManagerInfluencerDao dao = (ManagerInfluencerDao) transaction.getDao(DaoEnum.MANAGER_INFLUENCER);
        try {
            List<Influencer> influencerList = dao.readManagerInfluencers(managerId);
            for (Influencer influencer : influencerList) {
                buildInfluencer(influencer);
            }
            return influencerList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Influencer> readAll() throws ServiceException {
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        try {
            List<User> userList = userDao.readUsersByRole(UserRole.INFLUENCER);
            List<Influencer> influencerList = new ArrayList<>(userList.size());
            for (User user : userList) {
                Influencer influencer = new Influencer(user);
                buildInfluencer(influencer);
                influencerList.add(influencer);
            }
            return influencerList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildInfluencer(Influencer influencer) throws DaoException {
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);
        UserFileDao userFileDao = (UserFileDao) transaction.getDao(DaoEnum.FILE);
        SocialLinkDao socialLinkDao = (SocialLinkDao) transaction.getDao(DaoEnum.SOCIAL_LINK);
        ManagerInfluencerDao managerInfluencerDao = (ManagerInfluencerDao) transaction.getDao(DaoEnum.MANAGER_INFLUENCER);
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);

        Integer userId = influencer.getId();
        influencer.setUserInfo(userInfoDao.read(userId));
        influencer.getUserInfo().setUserFile(userFileDao.read(influencer.getUserInfo().getUserFile().getId()));
        influencer.setLinkList(socialLinkDao.readByUserId(userId));
        influencer.setManager(managerInfluencerDao.readManager(userId));
        influencer.setCampaignList(campaignDao.readAllByOwner(userId));
    }
}
