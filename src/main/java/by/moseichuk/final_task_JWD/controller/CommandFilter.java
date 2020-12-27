package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.controller.command.CampaignListCommand;
import by.moseichuk.final_task_JWD.controller.command.Login;
import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.final_task_JWD.service.ServiceFactory;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.impl.ServiceFactoryImpl;
import by.moseichuk.final_task_JWD.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CommandFilter.class);
    private static Map<String, Command> commandMap = new ConcurrentHashMap<>();

    static {
        commandMap.put("/login", new Login());
        commandMap.put("/campaign/list", new CampaignListCommand());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            int begin = request.getContextPath().length();
            //int end = request.getRequestURI().lastIndexOf('.');
            String commandName = request.getRequestURI().substring(begin);
            System.out.println("Command name " + commandName);
            Command command = commandMap.get(commandName);
            if (command != null) {
                command.setName(commandName);
                request.setAttribute("command", command);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.setAttribute("errorMessage", String.format("Ошибка сервера по адресу %s, нет комманды", request.getRequestURI()));
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            LOGGER.error(String.format("Impossible to perform request (Not HTTP request). From : %s. Host : %s. Port : %s",
                                       servletRequest.getRemoteAddr(),
                                       servletRequest.getRemoteHost(),
                                       servletRequest.getRemotePort()));
        }
    }

    @Override
    public void destroy() {

    }
}
