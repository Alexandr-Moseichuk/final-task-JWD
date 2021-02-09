package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.dao.exception.DaoException;

public interface UserCampaignDao {

    void unsubscribeAll(Integer userId) throws DaoException;

}
