package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.bean.UserStatus;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    
    List<Integer> readCampaignIds(Integer userId) throws DaoException;

    User readByEmail(String email) throws DaoException;

    List<User> readUsersByRole(UserRole userRole) throws DaoException;

    void updateStatus(List<Integer> idList, UserStatus userStatus) throws DaoException;
}
