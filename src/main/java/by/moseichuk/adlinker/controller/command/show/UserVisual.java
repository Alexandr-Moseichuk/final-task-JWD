package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
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

public class UserVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserVisual.class);

    {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> userList = userService.readAll();
            request.setAttribute("userList", userList);
            return new Forward("jsp/user/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("jsp/error.jsp", "Ошибка при чтении всех пользователей из БД");
            return new Forward("jsp/error.jsp");
        }
    }
}
