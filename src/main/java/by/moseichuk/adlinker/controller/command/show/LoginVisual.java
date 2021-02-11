package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginVisual extends Command {
    private static final String LOGIN_JSP = "jsp/login.jsp";
    private static final String CAMPAIGN_LIST_PATH = "/campaign/list.html";

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ResultPage(LOGIN_JSP);
        } else {
            User authorizedUser = (User) session.getAttribute(Attribute.AUTHORIZED_USER);
            if (authorizedUser == null) {
                return new ResultPage(LOGIN_JSP);
            } else {
                return new ResultPage(CAMPAIGN_LIST_PATH, true);
            }
        }
    }
}
