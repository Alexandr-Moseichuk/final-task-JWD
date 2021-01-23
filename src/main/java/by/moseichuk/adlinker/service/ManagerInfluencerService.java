package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.service.exception.ServiceException;

public interface ManagerInfluencerService {
    void create(Influencer influencer) throws ServiceException;
}
