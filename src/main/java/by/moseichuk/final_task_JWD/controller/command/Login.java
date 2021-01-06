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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Login extends Command {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);

    private static final Map<UserRole, List<MenuItem>> menuMap = new ConcurrentHashMap<>();

    static {
        menuMap.put(UserRole.ADMINISTRATOR, Arrays.asList(
                new MenuItem("Заявки","/registration_application/list"),
                new MenuItem("Пользователи", "/user/list")
        ));
        menuMap.put(UserRole.ADVERTISER, Arrays.asList(
                new MenuItem("Мои кампании", "/advertiser/campaign/list")
        ));
        menuMap.put(UserRole.INFLUENCER, Arrays.asList(
                new MenuItem("Рекламные кампании", "/influencer/campaign/list"),
                new MenuItem("Менеджер", "influencer/manager")
        ));
        menuMap.put(UserRole.MANAGER, Arrays.asList(
                new MenuItem("Рекламные кампании", "/manager/campaign/list"),
                new MenuItem("Инфлюенсеры", "/manager/influencer/list")
        ));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        if (mail != null && password != null) {
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            try {
                User user = userService.login(mail, password);
                if (user != null) {
                    request.getSession().setAttribute("authorizedUser", user);
                    request.getSession().setAttribute("menuList", menuMap.get(user.getRole()));
                    return new Forward("/campaign/list", true);
                } else {
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
}
