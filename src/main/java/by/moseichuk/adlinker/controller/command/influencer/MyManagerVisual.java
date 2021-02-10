package by.moseichuk.adlinker.controller.command.influencer;

import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ManagerService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyManagerVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(MyManagerVisual.class);
    private static final String ERROR_JSP = "jsp/error.jsp";

    public MyManagerVisual() {
        getPermissionSet().add(UserRole.INFLUENCER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService = (ManagerService) serviceFactory.getService(ServiceEnum.MANAGER);
        try {
            User user = (User) request.getSession(false).getAttribute("authorizedUser");
            Manager manager = managerService.readByInfluencerId(user.getId());
            String redirectPage = String.format("/user/profile.html?userId=%d", manager.getId());
            return new Forward(redirectPage, true);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("errorMessage", "Ошибка получения менеджера");
            return new Forward(ERROR_JSP);
        }
    }
}
