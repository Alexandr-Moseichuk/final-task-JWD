package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.dao.UserDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer create(User entity) throws DaoException {
        return null;
    }

    @Override
    public User read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(User entity) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public List<User> readAll() throws DaoException {
        return null;
    }

    @Override
    public List<Integer> readCampaignIds(Integer userId) throws DaoException {
        return null;
    }

    @Override
    public List<User> readManagerInfluencers(Integer managerId) throws DaoException {
        return null;
    }
}
