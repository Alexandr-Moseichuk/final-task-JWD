package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class Lang extends Command {
    private static final String COOKIE_NAME = "language";
    private static final String LOCALIZATION_PARAM = "loc";
    private static final String PREVIOUS_PAGE_ATTR = "previousPage";
    private static final String JSP_PATH = "/WEB-INF/jsp";
    private static final char DELIMITER = '.';

    public Lang() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, COOKIE_NAME);
        String localization = request.getParameter(LOCALIZATION_PARAM);

        if (cookie != null) {
            cookie.setValue(localization);
        } else {
            cookie = new Cookie(COOKIE_NAME, localization);
        }
        response.addCookie(cookie);

        String previousPage = request.getSession(false).getAttribute(PREVIOUS_PAGE_ATTR).toString();
        System.out.println(previousPage);
        int begin = request.getContextPath().length() + JSP_PATH.length();
        int end = previousPage.lastIndexOf(DELIMITER);
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
