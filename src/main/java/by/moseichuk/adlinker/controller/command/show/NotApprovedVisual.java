package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ApplicationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotApprovedVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(NotApprovedVisual.class);
    private static final String PERMISSION_DENIED_JSP = "jsp/permission_denied.jsp";
    private static final String NOT_APPROVED_JSP = "jsp/application/not_approved.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
        if (authorizedUser.getStatus() != UserStatus.UNVERIFIED) {
            return new Forward(PERMISSION_DENIED_JSP);
        }

        ApplicationService applicationService = (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            Application application = applicationService.read(authorizedUser.getId());
            request.setAttribute("applicationComment", application.getComment());
            return new Forward(NOT_APPROVED_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }
    }
}
