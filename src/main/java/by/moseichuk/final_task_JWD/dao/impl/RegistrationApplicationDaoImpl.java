package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.RegistrationApplication;
import by.moseichuk.final_task_JWD.dao.RegistrationApplicationDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public class RegistrationApplicationDaoImpl extends BaseDao implements RegistrationApplicationDao {
    @Override
    public Integer create(RegistrationApplication entity) throws DaoException {
        return null;
    }

    @Override
    public RegistrationApplication read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(RegistrationApplication entity) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public List<RegistrationApplication> readAll() throws DaoException {
        return null;
    }
}
