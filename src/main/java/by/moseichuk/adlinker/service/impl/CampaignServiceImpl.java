package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.dao.CampaignDao;
import by.moseichuk.adlinker.dao.DaoEnum;
import by.moseichuk.adlinker.dao.exception.DaoException;
import by.moseichuk.adlinker.service.BaseService;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public class CampaignServiceImpl extends BaseService implements CampaignService {
    @Override
    public List<Campaign> readAll() throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            return campaignDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            campaignDao.create(campaign);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            campaignDao.delete(campaign.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Campaign campaign) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            campaignDao.update(campaign);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Campaign> readSublist(int count, int offset) throws ServiceException {
        CampaignDao campaignDao = (CampaignDao) transaction.getDao(DaoEnum.CAMPAIGN);
        try {
            return campaignDao.readSublist(count, offset);
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
}