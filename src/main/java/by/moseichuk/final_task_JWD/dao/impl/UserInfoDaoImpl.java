package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.UserInfo;
import by.moseichuk.final_task_JWD.dao.UserInfoDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {
    private static final String CREATE = "INSERT INTO `user_info` (`user_id`, `last_name`, `first_name`, `second_name`, `description`, `phone_number`, `photo_id`) VALUES(?, ?, ?, ?, ?)";
    private static final String READ   = "SELECT `last_name`, `first_name`, `second_name`, `description`, `phone_number`, `photo_id` FROM `user_info` WHERE `user_id` = ?";
    private static final String UPDATE = "UPDATE `user_info` SET `last_name` = ?, `first_name` = ?, `second_name` = ?, `description` = ?, `phone_number` = ?, `photo_id` = ? WHERE `user_id` = ?";
    private static final String DELETE = "DELETE FROM `user_info` WHERE `user_id` = ?";
    private static final String READ_ALL = "SELECT * FROM `user_info`";

    @Override
    public Integer create(UserInfo entity) throws DaoException {
        return null;
    }

    @Override
    public UserInfo read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(UserInfo entity) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public List<UserInfo> readAll() throws DaoException {
        return null;
    }
}
