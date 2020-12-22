package by.moseichuk.final_task_JWD.service;

public interface ServiceFactory {
    <Type extends BaseService> Type getService(ServiceEnum serviceType);
}
