package by.moseichuk.adlinker.service;

public interface ServiceFactory {
    BaseService getService(ServiceEnum serviceType);

    void close();
}
