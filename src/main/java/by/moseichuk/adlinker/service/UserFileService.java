package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.UserFile;
import by.moseichuk.adlinker.service.exception.ServiceException;

public interface UserFileService {

    Integer create(UserFile userFile) throws ServiceException;

}
