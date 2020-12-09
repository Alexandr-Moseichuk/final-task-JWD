package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.bean.Entity;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;

public interface Dao<T extends Entity> {
    Integer create(T entity) throws DaoException;

    T read(Integer id) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
