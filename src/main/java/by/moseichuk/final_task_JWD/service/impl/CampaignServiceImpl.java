package by.moseichuk.final_task_JWD.service.impl;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.dao.CampaignDao;
import by.moseichuk.final_task_JWD.dao.DaoEnum;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.service.BaseService;
import by.moseichuk.final_task_JWD.service.CampaignService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

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
}
