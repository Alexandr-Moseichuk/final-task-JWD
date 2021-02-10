package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.servlet.ResultPage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Changing user language by setting new locale to cookies
 *
 * @author Alexandr Moseichuk
 */
public class Lang extends Command {
    private static final String COOKIE_NAME = "language";
    private static final String LOCALIZATION_PARAM = "loc";
    private static final String PREVIOUS_PAGE_ATTR = "previousPage";
    private static final String JSP_PATH = "/WEB-INF/jsp";
    private static final char DELIMITER = '.';
    private static final String POSTFIX = ".html";

    /**
     * Sets user role permissions
     */
    public Lang() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    /**
     * Changes localisation in cookies and redirects to current page
     *
     * @param request  http request
     * @param response http response
     * @return         redirect to current page
     */
    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, COOKIE_NAME);
        String localization = request.getParameter(LOCALIZATION_PARAM);

        if (cookie != null) {
            cookie.setValue(localization);
        } else {
            cookie = new Cookie(COOKIE_NAME, localization);
        }
        response.addCookie(cookie);

        String previousPage = request.getSession(false).getAttribute(PREVIOUS_PAGE_ATTR).toString();
        int begin = request.getContextPath().length() + JSP_PATH.length();
        int end = previousPage.lastIndexOf(DELIMITER);
        String redirect = previousPage.substring(begin, end);
        redirect = redirect.concat(POSTFIX);
        return new ResultPage(redirect, true);
    }

    /**
     * Searches cookie in request by name
     *
     * @param request http request
     * @param name    cookie name
     * @return        cookie object or null if there is no cookie with such name
     */
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
