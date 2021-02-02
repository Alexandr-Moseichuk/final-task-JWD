package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class Logout extends Command {
    private static final String PAGE_PATH = "/";

    public Logout() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new Forward(PAGE_PATH, true);
    }
}
