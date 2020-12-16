package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.bean.UserFile;
import by.moseichuk.final_task_JWD.dao.UserFileDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileDaoImpl extends BaseDao implements UserFileDao {
    private static final String CREATE = "INSERT INTO `file` (`path`) VALUES(?)";
    private static final String READ = "SELECT `path` FROM `file` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `file` SET `path` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `file` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `file`";

    @Override
    public Integer create(UserFile userFile) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userFile.getPath());

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
    public UserFile read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            UserFile userFile = null;
            if (resultSet.next()) {
                userFile = new UserFile();
                userFile.setId(id);
                userFile.setPath(resultSet.getString("path"));
            }
            return userFile;
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
    public void update(UserFile userFile) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, userFile.getPath());

            statement.setInt(4, userFile.getId());
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
    public List<UserFile> readAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);

            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();

            List<UserFile> userFileList = new ArrayList<>();
            while (resultSet.next()) {
                UserFile userFile = new UserFile();
                userFile.setId(resultSet.getInt("id"));
                userFile.setPath(resultSet.getString("path"));
                userFileList.add(userFile);
            }
            return userFileList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
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

}
