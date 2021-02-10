package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.MenuItem;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Login user by creating session and setting corresponding {@code User} object into session attributes
 *
 * @author Moseichuk Alexandr
 */
public class Login extends Command {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);
    private static final String SUCCESS_REDIRECT = "/campaign/list.html";
    private static final String FAILED_FORWARD = "jsp/login.jsp";

    /**
     * Searches user in BD by email and password. If user exists then sets corresponding user object
     * and list of menu items into session attributes
     *
     * @param request  http request
     * @param response http responce
     * @return
     */
    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOGGER.debug(email);
        LOGGER.debug(password);
        if (email != null && password != null) {
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            try {
                User user = userService.login(email, password);
                if (user != null) {
                    request.getSession().setAttribute("authorizedUser", user);
                    request.getSession().setAttribute("menuList", buildMenu(user.getRole()));
                    LOGGER.debug("Authorization success...");
                    return new ResultPage(SUCCESS_REDIRECT, true);
                } else {
                    LOGGER.debug("Authorization failed...");
                    request.setAttribute("email", email);
                    request.setAttribute("authorizationFailedMessage", "login.authorization_failed");
                    return new ResultPage(FAILED_FORWARD);
                }
            } catch (ServiceException e) {
                LOGGER.error(e.getMessage());
                request.getSession().setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
                return new ResultPage(Jsp.ERROR);
            }
        }
        return new ResultPage(FAILED_FORWARD);
    }

    /**
     * Builds user menu by role.
     *
     * @param userRole role
     * @return         list of {@code MenuItem}
     */
    private List<MenuItem> buildMenu(UserRole userRole) {
        List<MenuItem> menuItemList = new ArrayList<>();

        switch (userRole) {
            case ADMINISTRATOR:
                menuItemList.add(new MenuItem("menu.dropdown.reg_applications","/application/list.html"));
                menuItemList.add(new MenuItem("menu.dropdown.users", "/user/list.html"));
                return menuItemList;
            case ADVERTISER:
                menuItemList.add(new MenuItem("menu.dropdown.campaigns", "/advertiser/campaign/list.html"));
                menuItemList.add(new MenuItem("menu.dropdown.add_campaign", "/campaign/create.html"));
                return menuItemList;
            case INFLUENCER:
                menuItemList.add(new MenuItem("menu.dropdown.campaigns", "/influencer/campaign/list.html"));
                menuItemList.add(new MenuItem("menu.dropdown.manager", "/influencer/manager.html"));
                return menuItemList;
            case MANAGER:
                menuItemList.add(new MenuItem("menu.dropdown.campaigns", "/manager/campaign/list.html"));
                menuItemList.add(new MenuItem("menu.dropdown.influencers", "/manager/influencer/list.html"));
                return menuItemList;
            default:
                return menuItemList;
        }
    }
}
