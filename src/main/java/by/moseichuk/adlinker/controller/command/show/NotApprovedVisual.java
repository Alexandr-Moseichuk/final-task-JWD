package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
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

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        User authorizedUser = (User) request.getSession(false).getAttribute(Attribute.AUTHORIZED_USER);
        if (authorizedUser.getStatus() != UserStatus.UNVERIFIED) {
            return new ResultPage(PERMISSION_DENIED_JSP);
        }

        ApplicationService applicationService = (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            Application application = applicationService.read(authorizedUser.getId());
            request.setAttribute("applicationComment", application.getComment());
            return new ResultPage(NOT_APPROVED_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
