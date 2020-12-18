package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.bean.Influencer;
import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public interface ManagerInfluencerDao extends Dao<Influencer> {

    List<Influencer> readManagerInfluencers(Integer managerId) throws DaoException;

    User readManagerId(Integer influencerId) throws DaoException;

}
