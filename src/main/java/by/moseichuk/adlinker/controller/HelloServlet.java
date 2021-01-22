package by.moseichuk.adlinker.controller;

import by.moseichuk.adlinker.dao.exception.ConnectionPoolException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.pool.ConnectionPool;
import by.moseichuk.adlinker.service.impl.ServiceFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(HelloServlet.class);

    public void init() {
        try {
            ServiceFactoryImpl.init();
        } catch (Exception e) {
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
            request.setAttribute("pageURL", request.getRequestURL());
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
        ServiceFactoryImpl.destroy();
    }
}