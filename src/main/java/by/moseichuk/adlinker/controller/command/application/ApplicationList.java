package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
import by.moseichuk.adlinker.service.ApplicationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplicationList extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationList.class);

    public ApplicationList() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationService applicationService =
                (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            List<Application> applicationList = applicationService.readAll();
            request.setAttribute("applicationList", applicationList);
            return new Forward("jsp/application/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", "Ошибка получения заявок");
            return new Forward("jsp/error.jsp");
        }
    }
}