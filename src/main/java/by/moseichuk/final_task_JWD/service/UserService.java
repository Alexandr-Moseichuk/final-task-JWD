package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

public interface UserService {
    User login(String mail, String password) throws ServiceException;
}
