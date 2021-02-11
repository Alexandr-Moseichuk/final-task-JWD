package by.moseichuk.adlinker.controller.command.advertiser;

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

public class AdvertiserCampaignListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(AdvertiserCampaignListVisual.class);
    private static final String RESULT_JSP = "jsp/advertiser/campaign/list.jsp";
    private static final int PAGE_SIZE = 3;

    public AdvertiserCampaignListVisual() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            User authorizedUser = (User) request.getSession(false).getAttribute(Attribute.AUTHORIZED_USER);

            String currentPageParameter =  request.getParameter(Attribute.CURRENT_PAGE);
            int currentPage = 1;
            if (currentPageParameter != null) {
                currentPage = Integer.parseInt(currentPageParameter);
            }

            int offset = PaginationService.offset(PAGE_SIZE, currentPage);
            List<Campaign> campaignList = campaignService.readSublistByOwner(authorizedUser.getId(), PAGE_SIZE, offset);
            int totalRecords = campaignService.readRowCountByUser(authorizedUser.getId());
            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);
            request.setAttribute("campaignList", campaignList);
            request.setAttribute(Attribute.CURRENT_PAGE, currentPage);
            request.setAttribute(Attribute.LAST_PAGE, lastPage);
            return new ResultPage(RESULT_JSP);
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
