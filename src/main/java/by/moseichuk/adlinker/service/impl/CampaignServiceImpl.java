package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.dao.*;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CampaignServiceImpl extends BaseService implements CampaignService {
    @Override
    public void subscribe(User user, Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            campaignDao.subscribe(user, campaign);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Campaign> readAll() throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            List<Campaign> campaignList = campaignDao.readAll();
            for (Campaign campaign : campaignList) {
                buildCampaign(campaign);
            }
            return campaignList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer add(Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            Integer campaignId = campaignDao.create(campaign);
            transaction.commit();
            return campaignId;
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public Campaign read(Integer campaignId) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            Campaign campaign = campaignDao.read(campaignId);
            buildCampaign(campaign);
            return campaign;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void remove(Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            campaignDao.delete(campaign.getId());
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            campaignDao.update(campaign);
            transaction.commit();
        } catch (DaoException | TransactionException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Campaign> readSublist(int count, int offset) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            List<Campaign> campaignList = campaignDao.readSublist(count, offset);
            for (Campaign campaign : campaignList) {
               buildCampaign(campaign);
            }
            return campaignList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Campaign> readSublistByOwner(Integer ownerId, int limit, int offset) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            List<Campaign> campaignList = campaignDao.readSublistByOwner(ownerId, limit, offset);
            for (Campaign campaign : campaignList) {
                buildCampaign(campaign);
            }
            return campaignList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Campaign> readAllByOwner(Integer ownerId) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            List<Campaign> campaignList = campaignDao.readAllByOwner(ownerId);
            for (Campaign campaign : campaignList) {
                buildCampaign(campaign);
            }
            return campaignList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int readRowCount() throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            return campaignDao.readRowCount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int readRowCountByUser(Integer userId) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            return campaignDao.readRowCountByUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer readOwnerId(Integer campaignId) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            return campaignDao.readOwnerId(campaignId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildCampaign(Campaign campaign) throws DaoException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        UserDao userDao = (UserDao) transaction.getDao(DaoEnum.USER);
        UserInfoDao userInfoDao = (UserInfoDao) transaction.getDao(DaoEnum.USER_INFO);

        List<Integer> influencerIdList = campaignDao.readUserIds(campaign.getId());
        List<Influencer> influencerList = new ArrayList<>();
        for (Integer id : influencerIdList) {
            Influencer influencer = new Influencer(userDao.read(id));
            influencer.setUserInfo(userInfoDao.read(id));
            influencerList.add(influencer);
        }
        campaign.setInfluencerList(influencerList);

    }
}
