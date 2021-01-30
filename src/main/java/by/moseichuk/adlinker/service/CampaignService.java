package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public interface CampaignService {
    void subscribe(Influencer influencer, Campaign campaign) throws ServiceException;

    List<Campaign> readAll() throws ServiceException;

    void add(Campaign campaign) throws ServiceException;

    Campaign read(Integer campaignId) throws ServiceException;

    void remove(Campaign campaign) throws ServiceException;

    void update(Campaign campaign) throws ServiceException;

    List<Campaign> readSublist(int count, int offset) throws ServiceException;

    List<Campaign> readSublistByOwner(Integer ownerId, int limit, int offset) throws ServiceException;

    int readRowCount() throws ServiceException;

    Integer readOwnerId(Integer campaignId) throws ServiceException;
}
