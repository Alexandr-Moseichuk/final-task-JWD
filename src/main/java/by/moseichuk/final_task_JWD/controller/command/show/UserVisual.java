package by.moseichuk.final_task_JWD.controller.command.show;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserVisual.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> userList = userService.readAll();
            request.setAttribute("userList", userList);
            return new Forward("jsp/user/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error("Can't read all users: " + e.getMessage());
            request.setAttribute("jsp/error.jsp", "Ошибка при чтении всех пользователей из БД");
            return new Forward("jsp/error.jsp");
        }
    }
}
