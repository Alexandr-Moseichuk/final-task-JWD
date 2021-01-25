package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.service.exception.ServiceException;

public interface ManagerService {
    Manager readByInfluencerId(Integer influencerId) throws ServiceException;
}
