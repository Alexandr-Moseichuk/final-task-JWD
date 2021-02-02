package by.moseichuk.adlinker.controller.command.influencer;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
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

public class InfluencerSubscribe extends Command {
    private static final Logger LOGGER = LogManager.getLogger(InfluencerSubscribe.class);
    private static final String PERMISSION_DENIED_JSP = "jsp/permission_denied.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";
    private static final String INFLUENCER_LIST_PATH = "/influencer/list";
    private static final String LOGIN_PATH = "/login";

    public InfluencerSubscribe() {
        getPermissionSet().add(UserRole.MANAGER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser.getRole() != UserRole.MANAGER) {
                return new Forward(PERMISSION_DENIED_JSP);
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
                return new Forward(INFLUENCER_LIST_PATH, true);
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error(e);
                request.setAttribute("errorMessage", e.getMessage());
                return new Forward(ERROR_JSP);
            }
        } else {
            return new Forward(LOGIN_PATH, true);
        }
    }
}
