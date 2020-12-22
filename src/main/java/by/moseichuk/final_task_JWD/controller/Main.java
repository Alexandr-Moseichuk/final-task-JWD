package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.dao.Transaction;
import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.pool.ConnectionPool;
import by.moseichuk.final_task_JWD.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.final_task_JWD.service.impl.CampaignServiceImpl;
import by.moseichuk.final_task_JWD.service.impl.UserServiceImpl;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

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
            Transaction transaction = transactionFactory.createTransaction();
            CampaignServiceImpl service = new CampaignServiceImpl();
            service.setTransaction(transaction);

            List<Campaign> campaignList = service.readAll();
            for (Campaign c : campaignList) {
                System.out.println(c);
            }

            UserServiceImpl userService = new UserServiceImpl();
            userService.setTransaction(transaction);
            User user = userService.login("adminnnn@mail.ru", "admin");

            System.out.println(user);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
