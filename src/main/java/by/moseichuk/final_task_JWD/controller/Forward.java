package by.moseichuk.final_task_JWD.controller;

import java.util.HashMap;
import java.util.Map;

public class Forward {
    private static final String SUFFIX = ".html";
    private String pagePath;
    private boolean redirect;
    private Map<String, Object> attributes = new HashMap<>();

    public Forward(String pagePath, boolean redirect) {
        this.pagePath = pagePath;
        this.redirect = redirect;
    }

    public Forward(String pagePath) {
        this(pagePath, false);
    }

    public String getPagePath() {
        if (redirect) {
            return pagePath + SUFFIX;
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
