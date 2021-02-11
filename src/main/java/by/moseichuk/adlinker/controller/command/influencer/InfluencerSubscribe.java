package by.moseichuk.adlinker.controller.command.influencer;

import by.moseichuk.adlinker.bean.Influencer;
import by.moseichuk.adlinker.bean.Manager;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
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
    private static final String INFLUENCER_LIST_PATH = "/influencer/list.html";
    private static final String LOGIN_PATH = "/login.html";

    public InfluencerSubscribe() {
        getPermissionSet().add(UserRole.MANAGER);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authorizedUser = (User) session.getAttribute(Attribute.AUTHORIZED_USER);
            if (authorizedUser.getRole() != UserRole.MANAGER) {
                return new ResultPage(PERMISSION_DENIED_JSP);
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
                return new ResultPage(INFLUENCER_LIST_PATH, true);
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error(e.getMessage());
                request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
                return new ResultPage(Jsp.ERROR);
            }
        } else {
            return new ResultPage(LOGIN_PATH, true);
        }
    }
}
