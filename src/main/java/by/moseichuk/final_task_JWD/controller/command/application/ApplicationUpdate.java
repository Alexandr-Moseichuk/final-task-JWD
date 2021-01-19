package by.moseichuk.final_task_JWD.controller.command.application;

import by.moseichuk.final_task_JWD.bean.Application;
import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ApplicationService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;

public class ApplicationUpdate extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationUpdate.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("authorizedUser");
        Application application = new Application();
        application.setUserId(user.getId());
        application.setComment(request.getParameter("comment"));
        application.setDate(new GregorianCalendar());

        ApplicationService service = (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            service.update(application);
            request.setAttribute("updateFeedback", "Заявка обновлена");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("updateFeedback", "Произошла ошибка!");
        }
        return new Forward("jsp/application/not_approved.jsp");
    }
}
