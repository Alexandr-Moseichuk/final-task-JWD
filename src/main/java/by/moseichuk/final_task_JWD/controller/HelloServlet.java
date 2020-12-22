package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.controller.command.Login;
import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import by.moseichuk.final_task_JWD.dao.pool.ConnectionPool;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/adlinker_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final int DB_POOL_START_SIZE = 3;
    private static final int DB_POOL_SIZE = 3;
    private static final int DB_POOL_CONNECTION_TIMEOUT = 3;


    public void init() {
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL,
                                              DB_USERNAME, DB_PASSWORD,
                                              DB_POOL_START_SIZE, DB_POOL_SIZE,
                                              DB_POOL_CONNECTION_TIMEOUT);

        } catch (ConnectionPoolException e) {
            destroy();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("dopost");
            HttpSession session = request.getSession(false);
            Forward forward = new Forward("jsp/login.jsp");
            if (session != null) {
                forward = new Forward("jsp/campaign.jsp");
            }
            getServletContext().getRequestDispatcher(forward.getPagePath()).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("doPost");
            Forward forward = new Forward("jsp/login.jsp");
            Login login = new Login();
            forward = login.execute(request, response);

            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPagePath());
            } else {
                getServletContext().getRequestDispatcher(forward.getPagePath()).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}