package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public interface ManagerService {
    Manager readByInfluencerId(Integer influencerId) throws ServiceException;

    List<Campaign> readCampaigns(Integer managerId) throws ServiceException;
}
