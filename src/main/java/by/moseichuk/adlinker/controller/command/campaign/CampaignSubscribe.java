package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CampaignSubscribe extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CampaignSubscribe.class);
    private static final String RESULT_PATH = "/campaign/list";
    private static final String ERROR_JSP = "jsp/error.jsp";

    public CampaignSubscribe() {
        getPermissionSet().add(UserRole.INFLUENCER);
        getPermissionSet().add(UserRole.MANAGER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            Influencer influencer = new Influencer();
            influencer.setId(Integer.parseInt(request.getParameter("influencerId")));
            Campaign campaign = new Campaign();
            campaign.setId(Integer.parseInt(request.getParameter("campaignId")));

            campaignService.subscribe(influencer, campaign);
            return new Forward(RESULT_PATH, true);
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }

    }
}
