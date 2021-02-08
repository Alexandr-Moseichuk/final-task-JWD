package by.moseichuk.adlinker.controller.command.manager;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.InfluencerService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerInfluencersListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ManagerInfluencersListVisual.class);
    private static final String ERROR_JSP = "jsp/error.jsp";

    public ManagerInfluencersListVisual() {
        getPermissionSet().add(UserRole.MANAGER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        InfluencerService influencerService = (InfluencerService) serviceFactory.getService(ServiceEnum.INFLUENCER);
        try {
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            List<Influencer> influencerList = influencerService.readByManagerId(authorizedUser.getId());
            request.setAttribute("influencerList", influencerList);
            return new Forward("jsp/manager/influencer/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }
    }
}
