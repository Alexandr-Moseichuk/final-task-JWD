package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.controller.command.Login;
import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.dao.exception.ConnectionPoolException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.pool.ConnectionPool;
import by.moseichuk.final_task_JWD.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.final_task_JWD.service.ServiceFactory;
import by.moseichuk.final_task_JWD.service.impl.ServiceFactoryImpl;
import by.moseichuk.final_task_JWD.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(HelloServlet.class);

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
        executeRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        executeRequest(request, response);
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Command command = (Command) request.getAttribute("command");
            CommandManger commandManger = CommandManagerFactory.getInstance().getManager();
            Forward forward = commandManger.execute(command, request, response);
            commandManger.close();
            if (forward == null) {
                return;
            }
            if (forward.isRedirect()) {
                response.sendRedirect(request.getContextPath() + forward.getPagePath());
            } else {
                getServletContext().getRequestDispatcher(forward.getPagePath()).forward(request, response);
            }
        } catch (ServletException | TransactionException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", String.format("Ошибка сервера по адресу %s, описание ошибки %s", request.getRequestURI(), e.getMessage()));
            try {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            } catch (ServletException e1) {
                LOGGER.error(e1);
            }
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }
}