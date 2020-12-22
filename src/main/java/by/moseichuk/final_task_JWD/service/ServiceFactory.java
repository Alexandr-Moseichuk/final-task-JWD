package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.service.exception.ServiceException;

public interface ServiceFactory {
    <Type extends BaseService> Type getService(ServiceEnum serviceType) throws ServiceException;
}
