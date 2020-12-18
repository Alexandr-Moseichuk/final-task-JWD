package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.impl.CampaignDaoImpl;
import by.moseichuk.final_task_JWD.dao.pool.ConnectionPool;


public class Main {
    public static void main(String[] args) {
        //TODO test DB
        try {
            ConnectionPool.getInstance().init("org.mariadb.jdbc.Driver",
                    "jdbc:mariadb://localhost:3306/adlinker_db", "root", "root",
                    3, 5, 2);

            CampaignDaoImpl campaignDao = new CampaignDaoImpl();
            campaignDao.setConnection(ConnectionPool.getInstance().getConnection());
            Campaign campaign1 = campaignDao.read(1);
            Campaign campaign2 = campaignDao.read(2);
            System.out.println(campaign1);
            System.out.println(campaign2);


        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }
}
