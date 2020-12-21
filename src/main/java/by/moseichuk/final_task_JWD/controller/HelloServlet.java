package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import by.moseichuk.final_task_JWD.dao.pool.ConnectionPool;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
        try {
            ConnectionPool.getInstance().init("org.mariadb.jdbc.Driver",
                    "jdbc:mariadb://localhost:3306/adlinker_db", "root", "root",
                    3, 5, 2);
        } catch (ConnectionPoolException e) {
            destroy();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       //TODO
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO
    }

    public void destroy() {
    }
}