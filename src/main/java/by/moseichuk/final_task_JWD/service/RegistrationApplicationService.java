package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.RegistrationApplication;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public interface RegistrationApplicationService {

    List<RegistrationApplication> readAll() throws ServiceException;
}
