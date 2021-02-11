package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
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
    private static final String USER_PROFILE_JSP = "jsp/user/profile.jsp";

    public UserProfile(){
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        String userIdParameter = request.getParameter("userId");
        Integer userId;
        if (userIdParameter == null) {
            User authorizedUser = (User) request.getSession(false).getAttribute(Attribute.AUTHORIZED_USER);
            userId = authorizedUser.getId();
        } else {
            try {
                userId = Integer.parseInt(userIdParameter);
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
                request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
                return new ResultPage(Jsp.ERROR);
            }
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
                    request.setAttribute(Attribute.INFLUENCER_LIST, influencerList);
                    break;
            }
            return new ResultPage(USER_PROFILE_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
