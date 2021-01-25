package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.UserInfo;
import by.moseichuk.adlinker.service.exception.ServiceException;

public interface UserInfoService {

    void create(UserInfo userInfo) throws ServiceException;

    UserInfo read(Integer userId) throws ServiceException;

    UserInfo readManagerByUserId(Integer userId) throws ServiceException;
}
