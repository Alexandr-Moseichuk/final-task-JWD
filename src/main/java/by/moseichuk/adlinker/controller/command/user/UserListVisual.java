package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserListVisual.class);
    private static final String USER_LIST_JSP = "jsp/user/list.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";

    public UserListVisual() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> userList = userService.readAll();
            request.setAttribute("userList", userList);
            return new Forward(USER_LIST_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("jsp/error.jsp", "Ошибка при чтении всех пользователей из БД");
            return new Forward(ERROR_JSP);
        }
    }
}
