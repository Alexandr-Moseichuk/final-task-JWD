package by.moseichuk.final_task_JWD.controller;

import java.util.HashMap;
import java.util.Map;

public class Forward {
    private String pagePath;
    private boolean redirect;
    private Map<String, Object> attributes = new HashMap<>();

    public Forward(String pagePath, boolean redirect) {
        this.pagePath = "/WEB-INF/" + pagePath;
        this.redirect = redirect;
    }

    public Forward(String pagePath) {
        this(pagePath, true);
    }

    public String getPagePath() {
        return pagePath;
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
