package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class UserProfile extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserProfile.class);

    public UserProfile(){
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Integer userId;
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "User id is not a number");
            return new Forward("jsp/error.jsp");
        }

        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            User user = userService.read(userId);
            request.setAttribute("user", user);
            return new Forward("jsp/user/profile.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Can't read user profile");
            return new Forward("jsp/error.jsp");
        }
    }
}
