package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.RegistrationApplication;
import by.moseichuk.final_task_JWD.dao.RegistrationApplicationDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class RegistrationApplicationDaoImpl extends BaseDao implements RegistrationApplicationDao {
    private static final String CREATE = "INSERT INTO `registration_application` (`comment`, `date`) VALUES(?, ?)";
    private static final String READ   = "SELECT `comment`, `date` FROM `registration_application` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `registration_application` SET `comment` = ?, `date` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `registration_application` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `registration_application`";

    @Override
    public Integer create(RegistrationApplication application) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, application.getComment());
            preparedStatement.setDate(2, new Date(application.getDate().getTimeInMillis()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Can't get application ID");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public RegistrationApplication read(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            RegistrationApplication application = null;
            if (resultSet.next()) {
                application = new RegistrationApplication();
                application.setComment(resultSet.getString("comment"));
                application.setDate(parseDate(resultSet.getDate("date")));
            }
            return application;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(RegistrationApplication application) throws DaoException {
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
    public List<RegistrationApplication> readAll() throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            List<RegistrationApplication> applicationList = new ArrayList<>();
            while (resultSet.next()) {
                RegistrationApplication application = new RegistrationApplication();

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
