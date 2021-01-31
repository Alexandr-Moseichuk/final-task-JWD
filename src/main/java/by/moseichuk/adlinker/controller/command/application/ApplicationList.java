package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
import by.moseichuk.adlinker.service.ApplicationService;
import by.moseichuk.adlinker.service.PaginationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplicationList extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationList.class);
    private static final int PAGE_SIZE = 10;

    public ApplicationList() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationService applicationService =
                (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            String currentPageParameter =  request.getParameter("currentPage");
            int currentPage = 1;
            if (currentPageParameter != null) {
                currentPage = Integer.parseInt(currentPageParameter);
            }
            int offset = PaginationService.offset(PAGE_SIZE, currentPage);

            List<Application> applicationSubList = applicationService.readSubList(PAGE_SIZE, offset);
            int totalRecords = applicationService.readRowCount();
            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("lastPage", lastPage);
            request.setAttribute("applicationList", applicationSubList);
            return new Forward("jsp/application/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", "Ошибка получения заявок");
            return new Forward("jsp/error.jsp");
        }
    }
    /**
     * CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
     *         try {
     *             String currentPageParameter =  request.getParameter("currentPage");
     *             int currentPage = 1;
     *             if (currentPageParameter != null) {
     *                 currentPage = Integer.parseInt(currentPageParameter);
     *             }
     *
     *             int offset = PaginationService.offset(PAGE_SIZE, currentPage);
     *             List<Campaign> campaignSubList = campaignService.readSublist(PAGE_SIZE, offset);
     *             int totalRecords = campaignService.readRowCount();
     *             int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
     *             int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);
     *
     *             request.setAttribute("campaignSubList", campaignSubList);
     *             request.setAttribute("currentPage", currentPage);
     *             request.setAttribute("lastPage", lastPage);
     *             return new Forward("jsp/campaign/list.jsp");
     *         } catch (ServiceException | NumberFormatException e) {
     *             LOGGER.error(e);
     *             request.setAttribute("errorMessage", "Ошибка получения кампаний");
     *             return new Forward("jsp/error.jsp");
     *         }
     */
}
