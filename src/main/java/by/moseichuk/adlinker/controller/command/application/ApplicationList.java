package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
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
    private static final int PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE = 1;

    public ApplicationList() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationService applicationService =
                (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            String currentPageParameter =  request.getParameter(Attribute.CURRENT_PAGE);
            int currentPage = DEFAULT_PAGE;
            if (currentPageParameter != null) {
                currentPage = Integer.parseInt(currentPageParameter);
            }
            int offset = PaginationService.offset(PAGE_SIZE, currentPage);

            List<Application> applicationSubList = applicationService.readUnverifiedSubList(PAGE_SIZE, offset);
            int totalRecords = applicationService.readRowCount();
            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);

            request.setAttribute(Attribute.CURRENT_PAGE, currentPage);
            request.setAttribute(Attribute.LAST_PAGE, lastPage);
            request.setAttribute(Attribute.APPLICATION_LIST, applicationSubList);
            return new ResultPage(RESULT_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
