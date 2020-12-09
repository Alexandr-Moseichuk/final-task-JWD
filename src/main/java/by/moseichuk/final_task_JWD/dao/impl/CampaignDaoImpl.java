package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.dao.CampaignDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CampaignDaoImpl extends BaseDao implements CampaignDao {
    private static final String CREATE = "INSERT INTO `campaign` (`title`, `create_date`, `begin_date`, `end_date`, `description`, `requirement`, `budget`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT `title`, `create_date`, `begin_date`, `end_date`, `description`, `requirement`, `budget` FROM `campaign` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `campaign` SET `title` = ?, `create_date` = ?, `begin_date` = ?, `end_date` = ?, `description` = ?, `requirement` = ?, `budget` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `campaign` WHERE `id` = ?";

    @Override
    public Integer create(Campaign campaign) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, campaign.getTitle());
            statement.setDate(2, new Date(campaign.getCreateDate().getTimeInMillis()), campaign.getCreateDate());
            statement.setDate(3, new Date(campaign.getBeginDate().getTimeInMillis()), campaign.getBeginDate());
            statement.setDate(4, new Date(campaign.getEndDate().getTimeInMillis()), campaign.getEndDate());
            statement.setString(5, campaign.getDescription());
            statement.setString(6, campaign.getRequirement());
            statement.setBigDecimal(7, campaign.getBudget());
            statement.executeUpdate();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
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
    public Campaign read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            Campaign campaign = null;
            if (resultSet.next()) {
                campaign = new Campaign();
                campaign.setId(id);
                campaign.setTitle(resultSet.getString("title"));
                campaign.setCreateDate(parseDate(resultSet.getDate("create_date")));
                campaign.setBeginDate(parseDate(resultSet.getDate("begin_date")));
                campaign.setEndDate(parseDate(resultSet.getDate("end_date")));
                campaign.setDescription(resultSet.getString("description"));
                campaign.setRequirement(resultSet.getString("requirement"));
                campaign.setBudget(resultSet.getBigDecimal("budget"));

            }
            return campaign;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
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

    /**
     * "UPDATE `campaign` SET `title` = ?, `create_date` = ?, `begin_date` = ?, `end_date` = ?, `description` = ?, `requirement` = ?, `budget` = ? WHERE `id` = ?";
     */
    @Override
    public void update(Campaign campaign) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, campaign.getTitle());
            statement.setDate(2, new Date(campaign.getCreateDate().getTimeInMillis()));
            statement.setDate(3, new Date(campaign.getBeginDate().getTimeInMillis()));
            statement.setDate(4, new Date(campaign.getEndDate().getTimeInMillis()));
            statement.setString(5, campaign.getDescription());
            statement.setString(6, campaign.getRequirement());
            statement.setBigDecimal(7, campaign.getBudget());

            statement.setInt(8, campaign.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Calendar parseDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
