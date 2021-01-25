package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.util.List;

public interface ManagerInfluencerDao extends Dao<Influencer> {

    List<Influencer> readManagerInfluencers(Integer managerId) throws DaoException;

    Manager readManager(Integer influencerId) throws DaoException;

}
