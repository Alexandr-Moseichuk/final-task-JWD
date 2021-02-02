package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.PaginationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class CampaignListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CampaignListVisual.class);
    private static final String CAMPAIGN_LIST_JSP = "jsp/campaign/list.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";
    private static final int PAGE_SIZE = 3;


    public CampaignListVisual() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            String currentPageParameter =  request.getParameter("currentPage");
            int currentPage = 1;
            if (currentPageParameter != null) {
                currentPage = Integer.parseInt(currentPageParameter);
            }

            int offset = PaginationService.offset(PAGE_SIZE, currentPage);
            List<Campaign> campaignSubList = campaignService.readSublist(PAGE_SIZE, offset);
            int totalRecords = campaignService.readRowCount();
            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);

            request.setAttribute("campaignSubList", campaignSubList);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("lastPage", lastPage);
            return new Forward(CAMPAIGN_LIST_JSP);
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка получения кампаний");
            return new Forward(ERROR_JSP);
        }
    }
}
