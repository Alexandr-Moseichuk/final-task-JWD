package by.moseichuk.adlinker.tag;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class UserCardTag extends BodyTagSupport {
    private static final Logger LOGGER = LogManager.getLogger(UserCardTag.class);
    private String photoSrc;
    private User user;

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        UserInfo userInfo = user.getUserInfo();
        try {
            out.write("<div class=\"card ml-2 mr-2 mt-2 mb-2\" style=\"width:300px\">");
            out.write("<img class=\"card-img-top\" src=\"" + photoSrc + "\" alt=\"Card image\" style=\"width:100%\">");
            out.write("<div class=\"card-body\">");
            if (userInfo != null) {
                out.write(String.format("<h4>%s</h4>" ,userInfo.getLastName()));
                out.write(String.format("<h5>%s %s</h5>", userInfo.getFirstName(), userInfo.getSecondName()));
                out.write(String.format("<p class=\"card-text\">%s</p>", userInfo.getDescription()));
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        UserInfo userInfo = user.getUserInfo();
        try {
            out.write("</div>");
            out.write("<div class=\"card-footer\">");
            if (userInfo != null) {
                out.write("<h6>" + user.getUserInfo().getPhoneNumber() + "</h6>");
            }
            out.write("<h6>" + user.getRegistrationDate().getTime() + "</h6>");
            out.write("</div></div>");
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return EVAL_PAGE;
    }
}
