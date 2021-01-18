package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.MenuItem;
import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class Login extends Command {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        LOGGER.debug(mail);
        LOGGER.debug(password);
        if (mail != null && password != null) {
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            try {
                User user = userService.login(mail, password);
                if (user != null) {
                    request.getSession().setAttribute("authorizedUser", user);
                    request.getSession().setAttribute("menuList", buildMenu(user.getRole()));
                    LOGGER.debug("Authorization success...");
                    return new Forward("/campaign/list", true);
                } else {
                    LOGGER.debug("Authorization failed...");
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
        ResourceBundle rb = ResourceBundle.getBundle("localization.pagecontent");

        switch (userRole) {
            case ADMINISTRATOR:
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.reg_applications"),"/application/list.html"));
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.users"), "/user/list.html"));
                return menuItemList;
            case ADVERTISER:
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.campaigns"), "/advertiser/campaign/list.html"));
                return menuItemList;
            case INFLUENCER:
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.campaigns"), "/influencer/campaign/list.html"));
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.manager"), "/influencer/manager.html"));
                return menuItemList;
            case MANAGER:
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.campaigns"), "/manager/campaign/list.html"));
                menuItemList.add(new MenuItem(rb.getString("menu.dropdown.influencers"), "/manager/influencer/list.html"));
                return menuItemList;
            default:
                return menuItemList;
        }
    }
}
