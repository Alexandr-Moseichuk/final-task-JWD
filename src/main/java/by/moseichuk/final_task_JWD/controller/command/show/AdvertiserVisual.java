package by.moseichuk.final_task_JWD.controller.command.show;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdvertiserVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(AdvertiserVisual.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> advertiserList = userService.readUsersByRole(UserRole.ADVERTISER);
            request.setAttribute("advertiserList" ,advertiserList);
            return new Forward("jsp/advertiser/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка получения списка рекламодателей");
            return new Forward("jsp/error.jsp");
        }
    }
}
