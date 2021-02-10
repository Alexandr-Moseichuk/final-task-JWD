package by.moseichuk.adlinker.controller.command.manager;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ManagerInfluencerService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerSubscribe extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ManagerSubscribe.class);
    private static final String PERMISSION_DENIED_JSP = "jsp/permission_denied.jsp";
    private static final String MANAGER_LIST_PATH = "/manager/list.html";
    private static final String LOGIN_PATH = "/login.html";

    public ManagerSubscribe() {
        getPermissionSet().add(UserRole.INFLUENCER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser.getRole() != UserRole.INFLUENCER) {
                return new Forward(PERMISSION_DENIED_JSP);
            }
            Integer managerId;
            ManagerInfluencerService service =
                    (ManagerInfluencerService) serviceFactory.getService(ServiceEnum.MANAGER_INFLUENCER);
            try {
                managerId = Integer.parseInt(request.getParameter("managerId"));
                Influencer influencer = new Influencer();
                influencer.setId(authorizedUser.getId());
                Manager manager = new Manager();
                manager.setId(managerId);
                influencer.setManager(manager);
                service.create(influencer);
                return new Forward(MANAGER_LIST_PATH, true);
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error(e.getMessage());
                request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
                return new Forward(Jsp.ERROR);
            }
        } else {
            return new Forward(LOGIN_PATH, true);
        }
    }
}
