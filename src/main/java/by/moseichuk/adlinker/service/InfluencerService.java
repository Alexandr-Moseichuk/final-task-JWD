package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public interface InfluencerService {
    List<Influencer> readByManagerId(Integer managerId) throws ServiceException;
}
