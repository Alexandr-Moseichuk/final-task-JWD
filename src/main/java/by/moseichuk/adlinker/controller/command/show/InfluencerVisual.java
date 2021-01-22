package by.moseichuk.adlinker.controller.command.show;

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
import java.util.List;

public class InfluencerVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(InfluencerVisual.class);

    {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> influencerList = userService.readUsersByRole(UserRole.INFLUENCER);
            request.setAttribute("influencerList", influencerList);
            return new Forward("jsp/influencer/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка получения списка инфлюенсеров");
            return new Forward("jsp/error.jsp");
        }
    }
}