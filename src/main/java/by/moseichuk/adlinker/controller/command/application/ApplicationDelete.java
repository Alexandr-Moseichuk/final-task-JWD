package by.moseichuk.adlinker.controller.command.application;

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
import javax.servlet.http.HttpSession;

public class ApplicationDelete extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationDelete.class);
    private static final String ERROR_JSP = "jsp/error.jsp";
    private static final String RESULT_PAGE = "/";
    private static final String PERMISSION_DENIED_JSP = "jsp/permission_denied.jsp";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new Forward(PERMISSION_DENIED_JSP);
        }
        User authorizedUser = (User) session.getAttribute("authorizedUser");
        if (authorizedUser.getStatus() != UserStatus.UNVERIFIED) {
            return new Forward(PERMISSION_DENIED_JSP);
        }
        ApplicationService applicationService = (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            applicationService.delete(authorizedUser);
            session.invalidate();
            return new Forward(RESULT_PAGE, true);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }
    }
}
