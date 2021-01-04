package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.Influencer;
import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserInfo;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User login(String mail, String password) throws ServiceException;

    Integer create(User user) throws ServiceException;

    void createUserInfo(UserInfo userInfo) throws ServiceException;

    List<User> readAll() throws ServiceException;
    
    List<Influencer> readInfluencerList() throws ServiceException;
}
