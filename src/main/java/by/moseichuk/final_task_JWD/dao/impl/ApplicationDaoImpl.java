package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.Application;
import by.moseichuk.final_task_JWD.dao.ApplicationDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl extends BaseDao implements ApplicationDao {
    private static final String CREATE = "INSERT INTO `registration_application` (`user_id`, `comment`, `date`) VALUES(?, ?, ?)";
    private static final String READ   = "SELECT `comment`, `date` FROM `registration_application` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `registration_application` SET `comment` = ?, `date` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `registration_application` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `registration_application`";

    @Override
    public Integer create(Application application) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, application.getUserId());
            preparedStatement.setString(2, application.getComment());
            preparedStatement.setDate(3, new Date(application.getDate().getTimeInMillis()));
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
                application.setComment(resultSet.getString("comment"));
                application.setDate(parseDate(resultSet.getDate("date")));
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
            preparedStatement.setDate(2, new Date(application.getDate().getTimeInMillis()));
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
                application.setDate(parseDate(resultSet.getDate("date")));

                applicationList.add(application);
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
