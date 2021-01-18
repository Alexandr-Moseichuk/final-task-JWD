package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.Application;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public interface ApplicationService {

    void add(Application application) throws ServiceException;

    List<Application> readAll() throws ServiceException;

    void approveByIds(List<Integer> idList) throws ServiceException;

    void rejectByIds(List<Integer> idList) throws ServiceException;
}
