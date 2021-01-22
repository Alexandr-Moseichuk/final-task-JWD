package by.moseichuk.adlinker.controller.filter;

import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.command.*;
import by.moseichuk.adlinker.controller.command.Registration;
import by.moseichuk.adlinker.controller.command.application.ApplicationList;
import by.moseichuk.adlinker.controller.command.application.ApplicationListAction;
import by.moseichuk.adlinker.controller.command.application.ApplicationUpdate;
import by.moseichuk.adlinker.controller.command.show.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CommandFilter.class);
    private static Map<String, Command> commandGet = new HashMap<>();
    private static Map<String, Command> commandPost = new HashMap<>();
    //TODO PUT, DELETE... maps

    static {
        commandGet.put("/", new IndexVisual());
        commandGet.put("/login", new LoginVisual());
        commandGet.put("/logout", new Logout());
        commandGet.put("/campaign/list", new CampaignListVisual());
        commandGet.put("/registration", new RegistrationVisual());
        commandGet.put("/user/list", new UserVisual());
        commandGet.put("/influencer/list", new InfluencerVisual());
        commandGet.put("/advertiser/list", new AdvertiserVisual());
        commandGet.put("/manager/list", new ManagerVisual());
        commandGet.put("/lang", new Lang());
        commandGet.put("/application/list", new ApplicationList());
        commandGet.put("/application/update", new ApplicationUpdateVisual());

        commandPost.put("/login", new Login());
        commandPost.put("/registration", new Registration());
        commandPost.put("/application/action", new ApplicationListAction());
        commandPost.put("/application/update", new ApplicationUpdate());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            int begin = request.getContextPath().length();
            int end = request.getRequestURI().lastIndexOf('.');
            String commandName;
            if (end > begin) {
                commandName = request.getRequestURI().substring(begin, end);
            } else {
                commandName = request.getRequestURI().substring(begin);
            }
            LOGGER.debug("Method: " + request.getMethod() + " Command name: " + commandName + " Request URI: " + request.getRequestURI());
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
