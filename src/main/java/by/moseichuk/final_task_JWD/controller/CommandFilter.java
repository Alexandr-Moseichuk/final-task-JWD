package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.controller.command.Login;
import by.moseichuk.final_task_JWD.controller.command.Registration;
import by.moseichuk.final_task_JWD.controller.command.show.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CommandFilter.class);
    private static Map<String, Command> commandGet = new ConcurrentHashMap<>();
    private static Map<String, Command> commandPost = new ConcurrentHashMap<>();
    //TODO

    static {
        commandGet.put("/", new IndexVisual());
        commandGet.put("/login", new LoginVisual());
        commandGet.put("/campaign/list", new CampaignListVisual());
        commandGet.put("/registration", new RegistrationVisual());
        commandGet.put("/user/list", new UserVisual());

        commandPost.put("/login", new Login());
        commandPost.put("/registration", new Registration());
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
            Command command = detectCommand(request, commandName);
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

    private Command detectCommand(HttpServletRequest request, String commandName) {
        switch (request.getMethod().toUpperCase()) {
            case "GET":
                return commandGet.get(commandName);
            case "POST":
                return commandPost.get(commandName);
            default:
                return null;
        }
    }

    @Override
    public void destroy() {

    }
}
