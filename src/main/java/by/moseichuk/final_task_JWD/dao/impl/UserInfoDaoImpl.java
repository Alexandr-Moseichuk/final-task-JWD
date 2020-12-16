package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.UserFile;
import by.moseichuk.final_task_JWD.bean.UserInfo;
import by.moseichuk.final_task_JWD.dao.UserInfoDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {
    private static final String CREATE = "INSERT INTO `user_info` (`user_id`, `last_name`, `first_name`, `second_name`, `description`, `phone_number`, `photo_id`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String READ   = "SELECT `last_name`, `first_name`, `second_name`, `description`, `phone_number`, `photo_id` FROM `user_info` WHERE `user_id` = ?";
    private static final String UPDATE = "UPDATE `user_info` SET `last_name` = ?, `first_name` = ?, `second_name` = ?, `description` = ?, `phone_number` = ?, `photo_id` = ? WHERE `user_id` = ?";
    private static final String DELETE = "DELETE FROM `user_info` WHERE `user_id` = ?";
    private static final String READ_ALL = "SELECT * FROM `user_info`";

    @Override
    public Integer create(UserInfo userInfo) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, userInfo.getUserId());
            preparedStatement.setString(2, userInfo.getLastName());
            preparedStatement.setString(3, userInfo.getFirstName());
            preparedStatement.setString(4, userInfo.getSecondName());
            preparedStatement.setString(5, userInfo.getDescription());
            preparedStatement.setString(6, userInfo.getPhoneNumber());
            preparedStatement.setInt(7, userInfo.getUserFile().getId());

            preparedStatement.executeUpdate();
            return userInfo.getUserId();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public UserInfo read(Integer id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(id);
                userInfo.setLastName(resultSet.getString("last_name"));
                userInfo.setFirstName(resultSet.getString("first_name"));
                userInfo.setSecondName(resultSet.getString("second_name"));
                userInfo.setDescription((resultSet.getString("description")));
                userInfo.setPhoneNumber(resultSet.getString("phone_number"));
                UserFile userFile = new UserFile();
                userFile.setId(resultSet.getInt("photo_id"));
                userInfo.setUserFile(userFile);
                return userInfo;
            } else {
                throw new DaoException("Can't get user info from DB, ID = " + id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException | NullPointerException e) {
                //TODO
            }
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                //TODO
            }
        }
    }

    @Override
    public void update(UserInfo userInfo) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, userInfo.getLastName());
            preparedStatement.setString(2, userInfo.getFirstName());
            preparedStatement.setString(3, userInfo.getSecondName());
            preparedStatement.setString(4, userInfo.getDescription());
            preparedStatement.setString(5, userInfo.getPhoneNumber());
            preparedStatement.setInt(6, userInfo.getUserFile().getId());
            preparedStatement.setInt(7, userInfo.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserInfo> readAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            resultSet = preparedStatement.executeQuery();

            List<UserInfo> userInfoList = new ArrayList<>();
            while (resultSet.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(resultSet.getInt("user_id"));
                userInfo.setLastName(resultSet.getString("last_name"));
                userInfo.setFirstName(resultSet.getString("first_name"));
                userInfo.setSecondName(resultSet.getString("second_name"));
                userInfo.setDescription(resultSet.getString("description"));
                userInfo.setPhoneNumber(resultSet.getString("phone_number"));
                UserFile userFile = new UserFile();
                userFile.setId(resultSet.getInt("photo_id"));
                userInfo.setUserFile(userFile);
                userInfoList.add(userInfo);
            }
            return userInfoList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException | NullPointerException e) {
                //TODO
            }
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                //TODO
            }
        }
    }
}
