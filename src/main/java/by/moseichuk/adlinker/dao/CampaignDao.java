package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.util.List;

public interface CampaignDao extends Dao<Campaign> {
    void subscribe(Influencer influencer, Campaign campaign) throws DaoException;

    List<Integer> readCampaignFileIds(Integer id) throws DaoException;

    List<Integer> readUserIds(Integer id) throws DaoException;

    List<Campaign> readSublist(int limit, int offset) throws DaoException;

    List<Campaign> readSublistByOwner(Integer ownerId, int limit, int offset) throws DaoException;

    int readRowCount() throws DaoException;

    Integer readOwnerId(Integer campaignId) throws DaoException;
}
