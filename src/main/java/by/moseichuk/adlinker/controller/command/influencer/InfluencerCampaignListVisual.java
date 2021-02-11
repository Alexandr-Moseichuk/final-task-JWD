package by.moseichuk.adlinker.controller.command.influencer;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.PaginationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InfluencerCampaignListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(InfluencerCampaignListVisual.class);
    private static final String CAMPAIGN_LIST_JSP = "jsp/influencer/campaign/list.jsp";
    private static final int PAGE_SIZE = 5;

    public InfluencerCampaignListVisual() {
        getPermissionSet().add(UserRole.INFLUENCER);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            String currentPageParameter =  request.getParameter(Attribute.CURRENT_PAGE);
            int currentPage = 1;
            if (currentPageParameter != null) {
                currentPage = Integer.parseInt(currentPageParameter);
            }
            User authorizedUser = (User) request.getSession(false).getAttribute(Attribute.AUTHORIZED_USER);
            int userId = authorizedUser.getId();

            int offset = PaginationService.offset(PAGE_SIZE, currentPage);
            List<Campaign> campaignSubList = campaignService.readSublistByOwner(userId, PAGE_SIZE, offset);
            int totalRecords = campaignService.readRowCountByUser(userId);
            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);

            request.setAttribute("campaignSubList", campaignSubList);
            request.setAttribute(Attribute.CURRENT_PAGE, currentPage);
            request.setAttribute(Attribute.LAST_PAGE, lastPage);
            return new ResultPage(CAMPAIGN_LIST_JSP);
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }

    }
}
