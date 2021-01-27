package by.moseichuk.adlinker.dao.impl;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.dao.CampaignDao;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampaignDaoImpl extends BaseDao implements CampaignDao {
    private static final String CREATE = "INSERT INTO `campaign` (`title`, `create_date`, `begin_date`, `end_date`, `description`, `requirement`, `budget`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String READ   = "SELECT `id`, `title`, `create_date`, `begin_date`, `end_date`, `description`, `requirement`, `budget` FROM `campaign` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `campaign` SET `title` = ?, `create_date` = ?, `begin_date` = ?, `end_date` = ?, `description` = ?, `requirement` = ?, `budget` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `campaign` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `campaign`";

    private static final String READ_FILES_ID = "SELECT `file_id` FROM `campaign_file` WHERE `campaign_id` = ?";
    private static final String READ_USERS_ID = "SELECT `user_id` FROM `user_campaign` WHERE `campaign_id` = ?";
    private static final String READ_SUBLIST = "SELECT `id`, `title`, `create_date`, `begin_date`, `end_date`, `description`, `requirement`, `budget` FROM" +
            " `campaign` ORDER BY `id` LIMIT ? OFFSET ?";
    private static final String READ_ROW_COUNT = "SELECT COUNT(*) FROM `campaign`";


    @Override
    public Integer create(Campaign campaign) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, campaign.getTitle());
            statement.setTimestamp(2, new Timestamp(campaign.getCreateDate().getTimeInMillis()));
            statement.setTimestamp(3, new Timestamp(campaign.getBeginDate().getTimeInMillis()));
            statement.setTimestamp(4, new Timestamp(campaign.getEndDate().getTimeInMillis()));
            statement.setString(5, campaign.getDescription());
            statement.setString(6, campaign.getRequirement());
            statement.setBigDecimal(7, campaign.getBudget());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Campaign read(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            Campaign campaign = null;
            if (resultSet.next()) {
                campaign = buildCampaign(resultSet);
            }
            return campaign;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Campaign campaign) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, campaign.getTitle());
            statement.setTimestamp(2, new Timestamp(campaign.getCreateDate().getTimeInMillis()));
            statement.setTimestamp(3, new Timestamp(campaign.getBeginDate().getTimeInMillis()));
            statement.setTimestamp(4, new Timestamp(campaign.getEndDate().getTimeInMillis()));
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

    @Override
    public List<Campaign> readAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();

            List<Campaign> campaignList = new ArrayList<>();
            while (resultSet.next()) {
                campaignList.add(buildCampaign(resultSet));
            }
            return campaignList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public List<Integer> readCampaignFileIds(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_FILES_ID)) {
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();

            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getInt("file_id"));
            }
            return idList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Integer> readUserIds(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_USERS_ID)) {
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();

            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getInt("user_id"));
            }
            return idList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Campaign> readSublist(int limit, int offset) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_SUBLIST)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            List<Campaign> campaignList = new ArrayList<>();
            while (resultSet.next()) {
                campaignList.add(buildCampaign(resultSet));
            }
            return campaignList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int readRowCount() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_ROW_COUNT)) {
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Campaign buildCampaign(ResultSet resultSet) throws SQLException {
        Campaign campaign = new Campaign();
        campaign.setId(resultSet.getInt("id"));
        campaign.setTitle(resultSet.getString("title"));
        campaign.setCreateDate(parseDate(resultSet.getTimestamp("create_date")));
        campaign.setBeginDate(parseDate(resultSet.getTimestamp("begin_date")));
        campaign.setEndDate(parseDate(resultSet.getTimestamp("end_date")));
        campaign.setDescription(resultSet.getString("description"));
        campaign.setRequirement(resultSet.getString("requirement"));
        campaign.setBudget(resultSet.getBigDecimal("budget"));
        return campaign;
    }
}
