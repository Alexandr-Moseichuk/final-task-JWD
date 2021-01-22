package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserInfo;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User login(String mail, String password) throws ServiceException;

    Integer create(User user) throws ServiceException;

    void createUserInfo(UserInfo userInfo) throws ServiceException;

    List<User> readAll() throws ServiceException;
    
    List<User> readUsersByRole(UserRole userRole) throws ServiceException;
}