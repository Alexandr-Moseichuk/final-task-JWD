package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.impl.CampaignDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //TODO test DB
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/adlinker_db", "root", "root");
            CampaignDaoImpl campaignDao = new CampaignDaoImpl();
            campaignDao.setConnection(connection);
            Campaign campaign1 = campaignDao.read(1);
            Campaign campaign2 = campaignDao.read(2);
            System.out.println(campaign1);
            System.out.println(campaign2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
