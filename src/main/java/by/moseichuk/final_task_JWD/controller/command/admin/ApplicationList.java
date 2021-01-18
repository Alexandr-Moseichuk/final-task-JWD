package by.moseichuk.final_task_JWD.controller.command.admin;

import by.moseichuk.final_task_JWD.bean.Application;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ApplicationService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
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
                (ApplicationService) serviceFactory.getService(ServiceEnum.REGISTRATION_APPLICATION);
        try {
            List<Application> applicationList = applicationService.readAll();
            request.setAttribute("applicationList", applicationList);
            return new Forward("jsp/registration_application/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", "Ошибка получения заявок");
            return new Forward("jsp/error.jsp");
        }
    }
}
