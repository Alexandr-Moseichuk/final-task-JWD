package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.dao.exception.DaoException;

import java.util.List;

public interface Dao<T> {
    Integer create(T entity) throws DaoException;

    T read(Integer id) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;

    List<T> readAll() throws DaoException;
}
