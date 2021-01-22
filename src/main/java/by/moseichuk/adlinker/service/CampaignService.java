package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;

public interface CampaignService {
    List<Campaign> readAll() throws ServiceException;

    void add(Campaign campaign) throws ServiceException;

    void remove(Campaign campaign) throws ServiceException;

    void update(Campaign campaign) throws ServiceException;

    List<Campaign> readSublist(int count, int offset) throws ServiceException;

    int readRowCount() throws ServiceException;
}
