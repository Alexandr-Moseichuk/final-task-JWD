package by.moseichuk.final_task_JWD.service;

public interface ServiceFactory {
    BaseService getService(ServiceEnum serviceType);
}
