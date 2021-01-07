package by.moseichuk.final_task_JWD.service.impl;

import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.service.BaseService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.ServiceFactory;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.util.EnumMap;
import java.util.Map;

public class ServiceFactoryImpl implements ServiceFactory {
    private static Map<ServiceEnum, BaseService> serviceMap = new EnumMap<>(ServiceEnum.class);

    static {
        serviceMap.put(ServiceEnum.USER, new UserServiceImpl());
        serviceMap.put(ServiceEnum.CAMPAIGN, new CampaignServiceImpl());
    }

    private TransactionFactory transactionFactory;

    public ServiceFactoryImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    @Override
    public BaseService getService(ServiceEnum serviceType) {
        BaseService baseService = serviceMap.get(serviceType);
        baseService.setTransaction(transactionFactory.createTransaction());
        return baseService;
    }

    @Override
    public void close() {
        transactionFactory.close();
    }

}
