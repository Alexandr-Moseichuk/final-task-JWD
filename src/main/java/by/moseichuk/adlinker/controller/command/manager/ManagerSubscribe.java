package by.moseichuk.adlinker.controller.command.manager;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
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

    public ManagerSubscribe() {
        getPermissionSet().add(UserRole.INFLUENCER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User authorizedUser = (User) session.getAttribute("authorizedUser");
        if (authorizedUser.getRole() != UserRole.INFLUENCER) {
            return new Forward("jsp/permission_denied.jsp");
        }
        ManagerInfluencerService service = (ManagerInfluencerService) serviceFactory.getService(ServiceEnum.MANAGER_INFLUENCER);
        Manager manager = new Manager();
        manager.setId(Integer.parseInt(request.getParameter("managerId")));
        Influencer influencer = new Influencer();
        influencer.setId(authorizedUser.getId());
        influencer.setManager(manager);
        try {
            service.create(influencer);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward("jsp/error.jsp");
        }
        return new Forward("/manager/list", true);
    }
}
