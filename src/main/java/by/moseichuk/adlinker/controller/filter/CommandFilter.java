package by.moseichuk.adlinker.controller.filter;

import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.command.*;
import by.moseichuk.adlinker.controller.command.Registration;
import by.moseichuk.adlinker.controller.command.advertiser.AdvertiserCampaignListVisual;
import by.moseichuk.adlinker.controller.command.application.ApplicationList;
import by.moseichuk.adlinker.controller.command.application.ApplicationListAction;
import by.moseichuk.adlinker.controller.command.application.ApplicationUpdate;
import by.moseichuk.adlinker.controller.command.campaign.*;
import by.moseichuk.adlinker.controller.command.influencer.InfluencerCampaignListVisual;
import by.moseichuk.adlinker.controller.command.influencer.InfluencerSubscribe;
import by.moseichuk.adlinker.controller.command.manager.ManagerSubscribe;
import by.moseichuk.adlinker.controller.command.show.*;
import by.moseichuk.adlinker.controller.command.user.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CommandFilter} converts url request into {@code Command} object
 *
 * @author Alexandr Moseichuk
 */
public class CommandFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CommandFilter.class);
    /**
     * Map of available GET requests
     */
    private static final Map<String, Command> commandGet = new HashMap<>();
    /**
     * Map of available POST requests
     */
    private static final Map<String, Command> commandPost = new HashMap<>();

    /**
     * Puts all available GET, POST commands in maps
     *
     * @param filterConfig filter configuration
     * @throws ServletException if can't setup filter
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
        commandGet.put("/user/profile", new UserProfile());
        commandGet.put("/campaign/create", new CreateCampaignVisual());
        commandGet.put("/campaign/edit", new EditCampaignVisual());
        commandGet.put("/advertiser/campaign/list", new AdvertiserCampaignListVisual());
        commandGet.put("/influencer/campaign/list", new InfluencerCampaignListVisual());

        commandPost.put("/login", new Login());
        commandPost.put("/registration", new Registration());
        commandPost.put("/application/action", new ApplicationListAction());
        commandPost.put("/application/update", new ApplicationUpdate());
        commandPost.put("/influencer/subscribe", new InfluencerSubscribe());
        commandPost.put("/manager/subscribe", new ManagerSubscribe());
        commandPost.put("/campaign/create", new CreateCampaign());
        commandPost.put("/campaign/subscribe", new CampaignSubscribe());
        commandPost.put("/campaign/edit", new EditCampaign());
    }

    /**
     *
     *
     * @param servletRequest  http request
     * @param servletResponse http response
     * @param filterChain     chain of filters
     * @throws IOException    if can't forward request
     * @throws ServletException if can't proceed to the next element in the chain
     */
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

    /**
     * Returns command depending on request method, or null if method is not available
     *
     * @param request     http request
     * @param commandName http response
     * @return            command depending on request method, or null if method is not available
     */
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
