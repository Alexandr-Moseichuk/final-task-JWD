package by.moseichuk.adlinker.tag;

import by.moseichuk.adlinker.constant.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserRoleBadgeTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(UserRoleBadgeTag.class);
    private UserRole userRole;

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter jspWriter = pageContext.getOut();
        try {
            switch (userRole) {
                case INFLUENCER:
                    jspWriter.write(buildBadge("primary", userRole.getName()));
                    break;
                case ADVERTISER:
                    jspWriter.write(buildBadge("success", userRole.getName()));
                    break;
                case ADMINISTRATOR:
                    jspWriter.write(buildBadge("danger", userRole.getName()));
                    break;
                case MANAGER:
                    jspWriter.write(buildBadge("info", userRole.getName()));
                    break;
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    private String buildBadge(String type, String title) {
        return String.format("<span class=\"badge badge-pill badge-%s\">%s</span>", type, title);
    }
}
