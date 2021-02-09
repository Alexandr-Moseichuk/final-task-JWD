package by.moseichuk.adlinker.dao.impl;

import by.moseichuk.adlinker.dao.UserCampaignDao;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCampaignDaoImpl extends BaseDao implements UserCampaignDao {
    private static final String UNSUBSCRIBE_ALL_FROM_USER = "DELETE FROM `user_campaign` WHERE `user_id` = ?";

    @Override
    public void unsubscribeAll(Integer userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UNSUBSCRIBE_ALL_FROM_USER)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
