package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class IndexVisual extends Command {
    private static final String INDEX_JSP = "jsp/index.jsp";
    private static final String CAMPAIGN_LIST_PATH = "/campaign/list.html";

    public IndexVisual() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ResultPage(INDEX_JSP);
        } else {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser == null) {
                return new ResultPage(INDEX_JSP);
            } else {
                return new ResultPage(CAMPAIGN_LIST_PATH, true);
            }

        }
    }
}
