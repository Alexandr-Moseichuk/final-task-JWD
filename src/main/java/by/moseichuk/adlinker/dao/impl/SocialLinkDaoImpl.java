package by.moseichuk.adlinker.dao.impl;

import by.moseichuk.adlinker.bean.SocialLink;
import by.moseichuk.adlinker.dao.SocialLinkDao;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SocialLinkDaoImpl extends BaseDao implements SocialLinkDao {
    private static final String CREATE = "INSERT INTO `social_link` (`user_id`, `title`, `link`, `views`) VALUES(?, ?, ?, ?)";
    private static final String READ   = "SELECT `user_id`, `title`, `link`, `views` FROM `social_link` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `social_link` SET `user_id` = ?, `title` = ?, `link` = ?, `views` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `social_link` WHERE `user_id` = ?";
    private static final String READ_ALL = "SELECT `id`, `user_id`, `title`, `link`, `views` FROM `social_link`";
    private static final String READ_BY_USER_ID = "SELECT `id`, `title`, `link`, `views` FROM `social_link` WHERE `user_id` = ?";

    @Override
    public Integer create(SocialLink socialLink) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, socialLink.getUserId());
            preparedStatement.setString(2, socialLink.getTitle());
            preparedStatement.setString(3, socialLink.getLink());
            preparedStatement.setInt(4, socialLink.getViews());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Can't get new social link id");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while CREATE social link", e);
        }
    }

    @Override
    public SocialLink read(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                SocialLink socialLink = new SocialLink();
                socialLink.setId(id);
                socialLink.setTitle(resultSet.getString("title"));
                socialLink.setLink(resultSet.getString("link"));
                socialLink.setViews(resultSet.getInt("views"));
                return socialLink;
            } else {
                throw new DaoException("Can't read social link. ID = " + id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(SocialLink socialLink) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, socialLink.getUserId());
            preparedStatement.setString(2, socialLink.getTitle());
            preparedStatement.setString(3, socialLink.getLink());
            preparedStatement.setInt(4, socialLink.getViews());
            preparedStatement.setInt(5, socialLink.getId());

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
    public List<SocialLink> readAll() throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            List<SocialLink> links = new ArrayList<>();
            while (resultSet.next()) {
                SocialLink socialLink = new SocialLink();
                socialLink.setId(resultSet.getInt("id"));
                socialLink.setUserId(resultSet.getInt("user_id"));
                socialLink.setTitle(resultSet.getString("title"));
                socialLink.setLink(resultSet.getString("link"));
                socialLink.setViews(resultSet.getInt("views"));
                links.add(socialLink);
            }
            return links;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<SocialLink> readByUserId(Integer userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<SocialLink> links = new ArrayList<>();
            while (resultSet.next()) {
                SocialLink socialLink = new SocialLink();
                socialLink.setId(resultSet.getInt("id"));
                socialLink.setUserId(userId);
                socialLink.setTitle(resultSet.getString("title"));
                socialLink.setLink(resultSet.getString("link"));
                socialLink.setViews(resultSet.getInt("views"));
                links.add(socialLink);
            }
            return links;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
