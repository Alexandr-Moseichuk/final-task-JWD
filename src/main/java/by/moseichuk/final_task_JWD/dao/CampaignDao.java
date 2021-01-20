package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public interface CampaignDao extends Dao<Campaign> {
    List<Integer> readCampaignFileIds(Integer id) throws DaoException;

    List<Integer> readUserIds(Integer id) throws DaoException;

    List<Campaign> readSublist(int limit, int offset) throws DaoException;

    int readRowCount() throws DaoException;
}
