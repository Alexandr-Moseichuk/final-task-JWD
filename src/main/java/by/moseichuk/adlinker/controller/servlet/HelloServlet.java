package by.moseichuk.adlinker.controller.servlet;

import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.manager.CommandManagerFactory;
import by.moseichuk.adlinker.controller.manager.CommandManger;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.service.factory.ServiceFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

/**
 * Main servlet. Process all requests.
 *
 * @author Moseichuk Alexandr
 */
@MultipartConfig
public class HelloServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(HelloServlet.class);

    private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String PAGE_URL_ATTRIBUTE = "pageURL";
    private static final String COMMAND_ATTRIBUTE = "command";

    /**
     * Inits service factory. Otherwise destroys servlet
     */
    public void init() {
        try {
            ServiceFactoryImpl.init();
        } catch (Exception e) {
            destroy();
        }
    }

    /**
     * Process GET requests
     *
     * @param request      http request
     * @param response     http response
     * @throws IOException if cant send redirect or forward
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        executeRequest(request, response);
    }

    /**
     * Process POST requests
     *
     * @param request      http request
     * @param response     http response
     * @throws IOException if cant send redirect or forward
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        executeRequest(request, response);
    }

    /**
     * Executes command from request attributes.
     *
     * @param request      http request
     * @param response     http response
     * @throws IOException if cant send redirect or forward
     */
    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute(PAGE_URL_ATTRIBUTE, request.getRequestURL());
            Command command = (Command) request.getAttribute(COMMAND_ATTRIBUTE);
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
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
            try {
                getServletContext().getRequestDispatcher(ERROR_JSP).forward(request, response);
            } catch (ServletException e1) {
                LOGGER.error(e1);
            }
        }
    }

    /**
     * Destroys service factory.
     */
    public void destroy() {
        ServiceFactoryImpl.destroy();
    }
}