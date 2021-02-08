package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
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
    private static final String RESULT_JSP = "jsp/application/list.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";
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
            return new Forward(RESULT_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", "Ошибка получения заявок");
            return new Forward(ERROR_JSP);
        }
    }
}
