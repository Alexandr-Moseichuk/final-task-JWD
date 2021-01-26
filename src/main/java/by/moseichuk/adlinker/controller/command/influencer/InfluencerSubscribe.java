package by.moseichuk.adlinker.controller.command.influencer;

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

public class InfluencerSubscribe extends Command {
    private static final Logger LOGGER = LogManager.getLogger(InfluencerSubscribe.class);

    public InfluencerSubscribe() {
        getPermissionSet().add(UserRole.MANAGER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser.getRole() != UserRole.MANAGER) {
                return new Forward("jsp/permission_denied.jsp");
            }
            Integer influencerId;
            ManagerInfluencerService service =
                    (ManagerInfluencerService) serviceFactory.getService(ServiceEnum.MANAGER_INFLUENCER);
            try {
                influencerId = Integer.parseInt(request.getParameter("influencerId"));
                Influencer influencer = new Influencer();
                influencer.setId(influencerId);
                Manager manager = new Manager();
                manager.setId(authorizedUser.getId());
                influencer.setManager(manager);
                service.create(influencer);
                return new Forward("/influencer/list", true);
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error(e);
                request.setAttribute("errorMessage", e.getMessage());
                return new Forward("jsp/error.jsp");
            }
        } else {
            return new Forward("/login", true);
        }
    }
}
