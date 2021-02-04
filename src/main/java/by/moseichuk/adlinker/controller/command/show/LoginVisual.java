package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginVisual extends Command {
    private static final String LOGIN_JSP = "jsp/login.jsp";
    private static final String CAMPAIGN_LIST_PATH = "/campaign/list.html";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new Forward(LOGIN_JSP);
        } else {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser == null) {
                return new Forward(LOGIN_JSP);
            } else {
                return new Forward(CAMPAIGN_LIST_PATH, true);
            }
        }
    }
}
