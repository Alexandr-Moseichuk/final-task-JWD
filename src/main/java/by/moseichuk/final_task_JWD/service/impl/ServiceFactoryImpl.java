package by.moseichuk.final_task_JWD.service.impl;

import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.service.BaseService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.ServiceFactory;

public class ServiceFactoryImpl implements ServiceFactory {
    private TransactionFactory transactionFactory;

    public ServiceFactoryImpl(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    @Override
    public BaseService getService(ServiceEnum serviceType) {
        BaseService service = null;
        switch (serviceType) {
            case CAMPAIGN:
                service = new CampaignServiceImpl();
                break;
            case USER:
                service = new UserServiceImpl();
                break;
            case APPLICATION:
                service = new ApplicationServiceImpl();
                break;
        }
        service.setTransaction(transactionFactory.createTransaction());
        return service;
    }

    @Override
    public void close() {
        transactionFactory.close();
    }

}
