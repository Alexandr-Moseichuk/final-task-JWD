package by.moseichuk.adlinker.controller.command.show;

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
import java.util.Arrays;
import java.util.List;

public class InfluencerVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(InfluencerVisual.class);
    private static final String INFLUENCER_LIST_JSP = "jsp/influencer/list.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";

    public InfluencerVisual() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> influencerList = userService.readUsersByRole(UserRole.INFLUENCER);
            request.setAttribute("influencerList", influencerList);
            return new Forward(INFLUENCER_LIST_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка получения списка инфлюенсеров");
            return new Forward(ERROR_JSP);
        }
    }
}
