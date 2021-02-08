package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Logout user by deleting session
 *
 * @author Moseichuk Alexandr
 */
public class Logout extends Command {
    private static final String PAGE_PATH = "/";

    /**
     * Sets user role permissions
     */
    public Logout() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    /**
     * Invalidates session if exists and redirects to index page
     *
     * @param request  http request
     * @param response http response
     * @return         redirect to index page
     */
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new Forward(PAGE_PATH, true);
    }
}
