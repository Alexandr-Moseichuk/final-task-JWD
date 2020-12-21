package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    
    List<Integer> readCampaignIds(Integer userId) throws DaoException;

    User login(String mail, String password) throws DaoException;

}
