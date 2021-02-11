package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.ApplicationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;

public class ApplicationUpdate extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationUpdate.class);
    private static final String RESULT_PAGE = "/application/update.html";

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Attribute.AUTHORIZED_USER);
        Application application = new Application();
        application.setUserId(user.getId());
        application.setComment(request.getParameter("comment"));
        application.setDate(new GregorianCalendar());

        ApplicationService service = (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            service.update(application);
            request.setAttribute(Attribute.UPDATE_FEEDBACK, "Заявка обновлена");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.UPDATE_FEEDBACK, "Произошла ошибка!");
        }
        return new ResultPage(RESULT_PAGE, true);
    }
}
