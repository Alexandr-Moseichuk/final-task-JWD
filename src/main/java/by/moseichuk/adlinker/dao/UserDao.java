package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    
    List<Integer> readCampaignIds(Integer userId) throws DaoException;

    User readByEmail(String email) throws DaoException;

    List<User> readUsersByRole(UserRole userRole) throws DaoException;

    void updateStatus(List<Integer> idList, UserStatus userStatus) throws DaoException;
}
