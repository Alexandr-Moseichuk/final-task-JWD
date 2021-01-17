package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.RegistrationApplication;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public interface RegistrationApplicationService {

    void add(RegistrationApplication registrationApplication) throws ServiceException;

    List<RegistrationApplication> readAll() throws ServiceException;

    void approveByIds(List<Integer> idList) throws ServiceException;

    void rejectByIds(List<Integer> idList) throws ServiceException;
}
