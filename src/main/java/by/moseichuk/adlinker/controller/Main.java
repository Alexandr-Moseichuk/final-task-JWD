package by.moseichuk.adlinker.controller;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.dao.TransactionFactory;
import by.moseichuk.adlinker.dao.exception.ConnectionPoolException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.pool.ConnectionPool;
import by.moseichuk.adlinker.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.ServiceFactory;
import by.moseichuk.adlinker.service.impl.CampaignServiceImpl;
import by.moseichuk.adlinker.service.impl.ServiceFactoryImpl;
import by.moseichuk.adlinker.service.exception.ServiceException;

import java.util.List;


public class Main {
    public static void main(String[] args) {
//        //TODO test DB
//        try {
//            ConnectionPool.getInstance().init("org.mariadb.jdbc.Driver",
//                    "jdbc:mariadb://localhost:3306/adlinker_db", "root", "root",
//                    3, 5, 2);
//
//            CampaignDaoImpl campaignDao = new CampaignDaoImpl();
//            campaignDao.setConnection(ConnectionPool.getInstance().getConnection());
//            Campaign campaign1 = campaignDao.read(1);
//            Campaign campaign2 = campaignDao.read(2);
//            System.out.println(campaign1);
//            System.out.println(campaign2);
//
//
//        } catch (DaoException e) {
//            e.printStackTrace();
//        } catch (ConnectionPoolException e) {
//            e.printStackTrace();
//        }

        try {
            ConnectionPool.getInstance().init("org.mariadb.jdbc.Driver",
                    "jdbc:mariadb://localhost:3306/adlinker_db", "root", "root",
                    3, 5, 2);


            TransactionFactory transactionFactory = new TransactionFactoryImpl();
            ServiceFactory serviceFactory = new ServiceFactoryImpl(transactionFactory);
            CampaignServiceImpl service = (CampaignServiceImpl) serviceFactory.getService(ServiceEnum.CAMPAIGN);
            List<Campaign> campaignList = service.readAll();
            for (Campaign c : campaignList) {
                System.out.println(c);
            }
//
//            UserServiceImpl userService = new UserServiceImpl();
//            userService.setTransaction(transaction);
//            User user = userService.login("adminnnn@mail.ru", "admin");
//
//            System.out.println(user);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
