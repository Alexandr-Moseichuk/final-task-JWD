package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.*;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

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
            switch (user.getRole()) {
                case INFLUENCER:
                    ManagerService managerService = (ManagerService) serviceFactory.getService(ServiceEnum.MANAGER);
                    Manager manager = managerService.readByInfluencerId(user.getId());
                    request.setAttribute("manager", manager);
                    break;
                case MANAGER:
                    InfluencerService influencerService = (InfluencerService) serviceFactory.getService(ServiceEnum.INFLUENCER);
                    List<Influencer> influencerList = influencerService.readByManagerId(user.getId());
                    LOGGER.debug(influencerList);
                    request.setAttribute("influencerList", influencerList);
                    break;
            }
            return new Forward("jsp/user/profile.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Can't read user profile");
            return new Forward("jsp/error.jsp");
        }
    }
}
