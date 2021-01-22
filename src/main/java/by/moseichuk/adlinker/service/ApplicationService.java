package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public interface ApplicationService {

    void add(Application application) throws ServiceException;

    void update(Application application) throws ServiceException;

    List<Application> readAll() throws ServiceException;

    void approveByIds(List<Integer> idList) throws ServiceException;

    void rejectByIds(List<Integer> idList) throws ServiceException;
}
