package by.moseichuk.adlinker.controller.command.advertiser;

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
import java.util.List;

public class AdvertiserCampaignListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(AdvertiserCampaignListVisual.class);

    public AdvertiserCampaignListVisual() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            //TODO
            List<Campaign> campaignList = campaignService.readSublistByOwner(authorizedUser.getId(), 2, 1);
            request.setAttribute("campaignList", campaignList);
            return new Forward("jsp/advertiser/campaign/list.jsp");
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", "Ошибка получения заявок");
            return new Forward("jsp/error.jsp");
        }
    }
}
