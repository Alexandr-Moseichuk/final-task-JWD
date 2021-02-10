package by.moseichuk.adlinker.controller.command.influencer;

import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.ManagerService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyManagerVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(MyManagerVisual.class);

    public MyManagerVisual() {
        getPermissionSet().add(UserRole.INFLUENCER);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        ManagerService managerService = (ManagerService) serviceFactory.getService(ServiceEnum.MANAGER);
        try {
            User user = (User) request.getSession(false).getAttribute("authorizedUser");
            Manager manager = managerService.readByInfluencerId(user.getId());
            String redirectPage = String.format("/user/profile.html?userId=%d", manager.getId());
            return new ResultPage(redirectPage, true);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
