package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.util.List;

public interface ApplicationDao extends Dao<Application> {

    List<Application> readSubList(int count, int offset) throws DaoException;

    int readRowCount() throws DaoException;
}
