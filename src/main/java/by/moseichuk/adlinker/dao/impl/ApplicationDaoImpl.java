package by.moseichuk.adlinker.dao.impl;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.dao.ApplicationDao;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl extends BaseDao implements ApplicationDao {
    private static final String CREATE = "INSERT INTO `registration_application` (`user_id`, `comment`, `date`) VALUES(?, ?, ?)";
    private static final String READ   = "SELECT `comment`, `date` FROM `registration_application` WHERE `user_id` = ?";
    private static final String UPDATE = "UPDATE `registration_application` SET `comment` = ?, `date` = ? WHERE `user_id` = ?";
    private static final String DELETE = "DELETE FROM `registration_application` WHERE `user_id` = ?";
    private static final String READ_ALL = "SELECT `user_id`, `comment`, `date` FROM `registration_application`";

    private static final String READ_UNVERIFIED_SUBLIST = "SELECT `user_id`, `comment`, `date` FROM `registration_application` JOIN `user` ON `registration_application`.user_id = `user`.id WHERE `user`.status = 0 LIMIT ? OFFSET ?";
    private static final String READ_ROW_COUNT = "SELECT COUNT(*) FROM `campaign`";

    @Override
    public Integer create(Application application) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, application.getUserId());
            preparedStatement.setString(2, application.getComment());
            preparedStatement.setTimestamp(3, new Timestamp(application.getDate().getTimeInMillis()));
            preparedStatement.executeUpdate();

           return application.getUserId();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Application read(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            Application application = null;
            if (resultSet.next()) {
                application = new Application();
                application.setId(id);
                application.setComment(resultSet.getString("comment"));
                application.setDate(parseDate(resultSet.getTimestamp("date")));

            }
            return application;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Application application) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, application.getComment());
            preparedStatement.setTimestamp(2, new Timestamp(application.getDate().getTimeInMillis()));
            preparedStatement.setInt(3, application.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Application> readAll() throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            List<Application> applicationList = new ArrayList<>();
            while (resultSet.next()) {
                Application application = new Application();

                application.setUserId(resultSet.getInt("user_id"));
                application.setComment(resultSet.getString("comment"));
                application.setDate(parseDate(resultSet.getTimestamp("date")));

                applicationList.add(application);
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Application> readUnverifiedSubList(int count, int offset) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_UNVERIFIED_SUBLIST)) {
            statement.setInt(1, count);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            List<Application> applicationList = new ArrayList<>();
            while (resultSet.next()) {
                Application application = new Application();
                application.setUserId(resultSet.getInt("user_id"));
                application.setComment(resultSet.getString("comment"));
                application.setDate(parseDate(resultSet.getTimestamp("date")));
                applicationList.add(application);
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int readRowCount() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(READ_ROW_COUNT)){
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
}
