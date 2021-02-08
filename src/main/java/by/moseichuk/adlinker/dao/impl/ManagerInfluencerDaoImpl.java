package by.moseichuk.adlinker.dao.impl;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.dao.ManagerInfluencerDao;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ManagerInfluencerDaoImpl extends BaseDao implements ManagerInfluencerDao {
    private static final String CREATE = "INSERT INTO `manager_influencer` (`manager_id`, `influencer_id`, `begin_date`) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE `manager_influencer` SET `end_date` = ? WHERE `manager_id` = ? AND `influencer_id` = ? AND `begin_date` = ?";

    private static final String READ_INFLUENCER_IDS = "SELECT id, email, password, role, registration_date, status FROM manager_influencer JOIN user ON manager_influencer.influencer_id = user.id WHERE manager_id = ? AND end_date = 0";
    private static final String READ_MANAGER = "SELECT id, email, password, role, registration_date, status FROM manager_influencer JOIN user ON manager_influencer.manager_id = user.id WHERE influencer_id = ? AND end_date = 0";


    @Override
    public Integer create(Influencer influencer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, influencer.getManager().getId());
            preparedStatement.setInt(2, influencer.getId());
            preparedStatement.setTimestamp(3, new Timestamp(new GregorianCalendar().getTimeInMillis()));

            preparedStatement.executeUpdate();

            return influencer.getId();
        } catch (SQLException e) {
            throw new DaoException("Can't create manager-influencer link " + e.getMessage(), e);
        }
    }

    @Override
    public Influencer read(Integer id) throws DaoException {
        throw new DaoException("Unavailable operation");
    }

    @Override
    public void update(Influencer influencer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, influencer.getManager().getId());
            preparedStatement.setInt(3, influencer.getId());
            preparedStatement.setTimestamp(4, new Timestamp(influencer.getManagerBeginDate().getTimeInMillis()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't set end date", e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        throw new DaoException("Unavailable operation");
    }

    @Override
    public List<Influencer> readAll() throws DaoException {
        throw new DaoException("Unavailable operation");
    }

    @Override
    public List<Influencer> readManagerInfluencers(Integer managerId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_INFLUENCER_IDS)) {
            preparedStatement.setInt(1, managerId);

            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            List<Influencer> userList = new ArrayList<>();
            while (resultSet.next()) {
                Influencer influencer = new Influencer();
                influencer.setId(resultSet.getInt("id"));
                influencer.setEmail(resultSet.getString("email"));
                influencer.setPassword(resultSet.getString("password"));
                influencer.setRole(UserRole.values()[resultSet.getInt("role")]);
                influencer.setRegistrationDate(parseDate(resultSet.getTimestamp("registration_date")));
                influencer.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                userList.add(influencer);
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Manager readManager(Integer influencerId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_MANAGER)) {
            preparedStatement.setInt(1, influencerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Manager manager = new Manager();
                manager.setId(resultSet.getInt("id"));
                manager.setEmail(resultSet.getString("email"));
                manager.setPassword(resultSet.getString("password"));
                manager.setRole(UserRole.values()[resultSet.getInt("role")]);
                manager.setRegistrationDate(parseDate(resultSet.getTimestamp("registration_date")));
                manager.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                return manager;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
