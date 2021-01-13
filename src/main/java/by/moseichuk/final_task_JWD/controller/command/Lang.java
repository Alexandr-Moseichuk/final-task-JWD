package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class Lang extends Command {

    public Lang() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, "language");

        if (cookie != null) {
            cookie.setValue(request.getParameter("loc"));
        } else {
            cookie = new Cookie("language", request.getParameter("loc"));
        }
        response.addCookie(cookie);

        //int end = request.getRequestURI().lastIndexOf('.');
        String previousPage = request.getSession(false).getAttribute("previousPage").toString();
        System.out.println(previousPage);
        int begin = request.getContextPath().length() + "/WEB-INF/jsp".length();
        int end = previousPage.lastIndexOf('.');
        String redirect = previousPage.substring(begin, end);
        return new Forward(redirect, true);
    }

    private Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }
}
