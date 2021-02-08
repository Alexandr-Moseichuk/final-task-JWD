package by.moseichuk.adlinker.controller.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains forward path. Or can be redirect if {@code redirect} true
 *
 * @author Moseichuk Alexadr
 */
public class Forward {
    private String pagePath;
    private boolean redirect;
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * Constructs forward with path and sets redirect by boolean value
     *
     * @param pagePath forward path
     * @param redirect {@code true} if redirect, {@code false} if forward
     */
    public Forward(String pagePath, boolean redirect) {
        this.pagePath = pagePath;
        this.redirect = redirect;
    }

    /**
     * Construct forward with path
     *
     * @param pagePath forward path
     */
    public Forward(String pagePath) {
        this(pagePath, false);
    }

    /**
     * Returns page path depending on {@code redirect} value
     *
     * @return page path if redirect true, appends "/WEB-INF/" if forward
     */
    public String getPagePath() {
        if (redirect) {
            return pagePath;
        } else {
            return "/WEB-INF/" + pagePath;
        }
    }


    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
