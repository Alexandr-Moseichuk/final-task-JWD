package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCampaignVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditCampaignVisual.class);

    public EditCampaignVisual() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            Integer campaignId = Integer.parseInt(request.getParameter("campaignId"));
            Integer ownerId = campaignService.readOwnerId(campaignId);
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            if (!authorizedUser.getId().equals(ownerId)) {
                return new Forward("jsp/permission_denied.jsp");
            }
            Campaign campaign = campaignService.read(campaignId);
            request.setAttribute("campaign", campaign);
            return new Forward("jsp/campaign/edit.jsp");
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward("jsp/error.jsp");
        }
    }
}
