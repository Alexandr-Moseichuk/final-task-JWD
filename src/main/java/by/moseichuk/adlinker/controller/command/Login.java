package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.MenuItem;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class Login extends Command {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
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
                    return new Forward("/campaign/list", true);
                } else {
                    LOGGER.debug("Authorization failed...");
                    request.setAttribute("email", email);
                    request.setAttribute("authorizationFailedMessage", "login.authorization_failed");
                    return new Forward("jsp/login.jsp");
                }
            } catch (ServiceException e) {
                LOGGER.error(e);
                request.getSession().setAttribute("errorMessage", "Ошибка авторизации! Свяжитесь с администратором.");
                return new Forward("jsp/error.jsp");
            }
        }
        return new Forward("jsp/login.jsp");
    }

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
