package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.controller.command.Login;
import by.moseichuk.final_task_JWD.dao.TransactionFactory;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandFilter implements Filter {
    private static Map<String, Class<? extends Command>> commandMap = new ConcurrentHashMap<>();

    static {
        commandMap.put("/login", Login.class);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String method = request.getMethod();

            int begin = request.getContextPath().length();
            String commandName = request.getRequestURI().substring(begin);
            Class<? extends Command> commandClass = commandMap.get(commandName);
            try {
                Command command = commandClass.newInstance();
                command.setName(commandName);
                request.setAttribute("command", command);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (InstantiationException | IllegalAccessException e) {
                //TODO logger
                request.setAttribute("errorMessage", String.format("Ошибка сервера по адресу %s, описание ошибки %s", request.getRequestURI(), e.getMessage()));
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
            }

        } else {
            //TODO logger(not HTTP request)
        }
    }

    @Override
    public void destroy() {

    }
}
