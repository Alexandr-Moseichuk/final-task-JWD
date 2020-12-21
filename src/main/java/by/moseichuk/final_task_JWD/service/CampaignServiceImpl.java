package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.dao.CampaignDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public class CampaignServiceImpl extends BaseService implements CampaignService {
    @Override
    public List<Campaign> readAll() throws ServiceException {
        try {
            CampaignDao campaignDao = transaction.getDao("CampaignDao");
            return campaignDao.readAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(Campaign campaign) throws ServiceException {

    }

    @Override
    public void remove(Campaign campaign) throws ServiceException {

    }

    @Override
    public void update(Campaign campaign) throws ServiceException {

    }
}
