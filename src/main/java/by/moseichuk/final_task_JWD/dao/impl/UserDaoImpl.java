package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.bean.UserStatus;
import by.moseichuk.final_task_JWD.dao.UserDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    private static final String CREATE = "INSERT INTO `user` (`mail`, `password`, `role`, `registration_date`, `status`) VALUES(?, ?, ?, ?, ?)";
    private static final String READ   = "SELECT `mail`, `password`, `role`, `registration_date`, `status` FROM `user` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `user` SET `mail` = ?, `password` = ?, `role` = ?, `registration_date` = ?, `status` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `user` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `user`";

    private static final String READ_CAMPAIGN_IDS = "SELECT `campaign_id` FROM `user_campaign` WHERE `user_id` = ?";
    private static final String LOGIN = "SELECT `id`, `password`, `role`, `registration_date`, `status` FROM `user` WHERE `mail` = ?";
    private static final String READ_USERS_BY_ROLE = "SELECT `id`, `mail`, `registration_date`, `status` FROM `user` WHERE `role` = ?";
    private static final String UPDATE_STATUS = "UPDATE `user` SET `status` = ? WHERE `id` = ?";

    @Override
    public Integer create(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole().ordinal());
            preparedStatement.setDate(4, new Date(user.getRegistrationDate().getTimeInMillis()));
            preparedStatement.setInt(5, user.getStatus().ordinal());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Can't get new user ID");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User read(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                User user = new User();
                user.setId(id);
                user.setEmail(resultSet.getString("mail"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(UserRole.values()[resultSet.getInt("role")]);
                user.setRegistrationDate(parseDate(resultSet.getDate("registration_date")));
                user.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                return user;
            } else {
                throw new DaoException("Can't read user from DB, ID = " + id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole().ordinal());
            preparedStatement.setDate(4, new Date(user.getRegistrationDate().getTimeInMillis()));
            preparedStatement.setInt(5, user.getStatus().ordinal());
            preparedStatement.setInt(6, user.getId());

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
    public List<User> readAll() throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("mail"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(UserRole.values()[resultSet.getInt("role")]);
                user.setRegistrationDate(parseDate(resultSet.getDate("registration_date")));
                user.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Integer> readCampaignIds(Integer userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_CAMPAIGN_IDS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            List<Integer> integerList = new ArrayList<>();
            while (resultSet.next()) {
                integerList.add(resultSet.getInt("campaign_id"));
            }
            return integerList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User readByEmail(String mail) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(LOGIN)) {
            preparedStatement.setString(1, mail);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(mail);
                user.setPassword(resultSet.getString("password"));
                user.setRole(UserRole.values()[resultSet.getInt("role")]);
                user.setRegistrationDate(parseDate(resultSet.getDate("registration_date")));
                user.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Can't LOGIN user", e);
        }
    }

    @Override
    public List<User> readUsersByRole(UserRole userRole) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_USERS_BY_ROLE)) {
            preparedStatement.setInt(1, userRole.ordinal());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("mail"));
                user.setRegistrationDate(parseDate(resultSet.getDate("registration_date")));
                user.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                user.setRole(userRole);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException("Can't READ users by role", e);
        }
    }

    @Override
    public void updateStatus(List<Integer> idList, UserStatus userStatus) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)) {
            for (Integer id : idList) {
                preparedStatement.setInt(1, userStatus.ordinal());
                preparedStatement.setInt(2, id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("Can't UPDATE user status", e);
        }
    }

}
