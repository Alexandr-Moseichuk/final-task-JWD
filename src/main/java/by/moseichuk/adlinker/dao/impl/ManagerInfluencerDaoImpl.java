package by.moseichuk.adlinker.dao.impl;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.dao.ManagerInfluencerDao;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ManagerInfluencerDaoImpl extends BaseDao implements ManagerInfluencerDao {
    private static final String CREATE = "INSERT INTO `manager_influencer` (`manager_id`, `influencer_id`, `begin_date`) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE `manager_influencer` SET `end_date` = ? WHERE `manager_id` = ?, `influencer_id` = ?, `begin_date` = ?";

    private static final String READ_INFLUENCER_IDS = "SELECT `influencer_id` FROM `manager_influencer` WHERE `manager_id` = ?";
    private static final String READ_MANAGER_ID = "SELECT `manager_id` FROM `manager_influencer` WHERE `influencer_id` = ?";


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
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(2, influencer.getManager().getId());
            preparedStatement.setInt(3, influencer.getId());
            preparedStatement.setDate(4, new Date(influencer.getManagerBeginDate().getTimeInMillis()));

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
                influencer.setId(resultSet.getInt("influencer_id"));
                userList.add(influencer);
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User readManagerId(Integer influencerId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_MANAGER_ID)) {
            preparedStatement.setInt(1, influencerId);

            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("influencer_id"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
