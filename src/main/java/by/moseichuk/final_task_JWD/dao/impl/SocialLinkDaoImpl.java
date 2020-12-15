package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.dao.SocialLinkDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public class SocialLinkDaoImpl implements SocialLinkDao {
    private static final String CREATE = "INSERT INTO `social_link` (`user_id`, `title`, `link`, `views`) VALUES(?, ?, ?, ?)";
    private static final String READ   = "SELECT `user_id`, `title`, `link`, `views` FROM `social_link` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `social_link` SET `user_id` = ?, `title` = ?, `link` = ?, `views` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `social_link` WHERE `id` = ?";
    private static final String READ_ALL = "SELECT * FROM `social_link`";

    @Override
    public Integer create(SocialLinkDao entity) throws DaoException {
        return null;
    }

    @Override
    public SocialLinkDao read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(SocialLinkDao entity) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public List<SocialLinkDao> readAll() throws DaoException {
        return null;
    }
}
