package by.moseichuk.adlinker.controller.command.manager;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.*;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerCampaingsListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ManagerCampaingsListVisual.class);
    private static final String ERROR_JSP = "jsp/error.jsp";

    public ManagerCampaingsListVisual() {
        getPermissionSet().add(UserRole.MANAGER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        InfluencerService influencerService = (InfluencerService) serviceFactory.getService(ServiceEnum.INFLUENCER);
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            List<Influencer> influencerList = influencerService.readByManagerId(authorizedUser.getId());
            for (Influencer influencer : influencerList) {
                influencer.setCampaignList(campaignService.readAllByOwner(influencer.getId()));
            }
            LOGGER.debug(influencerList);
            request.setAttribute("influencerList", influencerList);
            return new Forward("jsp/manager/campaign/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }
    }
}
