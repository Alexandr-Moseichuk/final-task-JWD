package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.RegistrationApplication;
import by.moseichuk.final_task_JWD.dao.RegistrationApplicationDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistrationApplicationDaoImpl extends BaseDao implements RegistrationApplicationDao {
    private static final String CREATE = "INSERT INTO `registration_application` (`comment`) VALUES(?)";
    private static final String READ   = "SELECT `comment` FROM `registration_application` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `registration_application` SET `comment` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `registration_application` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `registration_application`";

    @Override
    public Integer create(RegistrationApplication application) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, application.getComment());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Can't get application ID");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                //TODO
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                //TODO
            }
        }
    }

    @Override
    public RegistrationApplication read(Integer id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();

            RegistrationApplication application = null;
            if (resultSet.next()) {
                application = new RegistrationApplication();
                application.setComment(resultSet.getString("comment"));
            }
            return application;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                //TODO
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                //TODO
            }
        }
    }

    @Override
    public void update(RegistrationApplication application) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, application.getComment());
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();

            List<RegistrationApplication> applicationList = new ArrayList<>();
            while (resultSet.next()) {
                RegistrationApplication application = new RegistrationApplication();

                application.setComment(resultSet.getString("comment"));

                applicationList.add(application);
            }
            return applicationList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                //TODO
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                //TODO
            }
        }
    }
}
