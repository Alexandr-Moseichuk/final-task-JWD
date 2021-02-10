package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCampaignVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditCampaignVisual.class);
    private static final String PERMISSION_DENIED_JSP = "jsp/permission_denied.jsp";
    private static final String EDIT_JSP = "jsp/campaign/edit.jsp";

    public EditCampaignVisual() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            Integer campaignId = Integer.parseInt(request.getParameter("campaignId"));
            Integer ownerId = campaignService.readOwnerId(campaignId);
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            if (!authorizedUser.getId().equals(ownerId)) {
                return new ResultPage(PERMISSION_DENIED_JSP);
            }
            Campaign campaign = campaignService.read(campaignId);
            request.setAttribute("campaign", campaign);
            return new ResultPage(EDIT_JSP);
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
