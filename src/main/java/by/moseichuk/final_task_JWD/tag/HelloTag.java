package by.moseichuk.final_task_JWD.tag;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.bean.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloTag extends TagSupport {
    private Campaign campaign;

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public int doStartTag() throws JspException {
        String message = "Campaign, " + campaign.getTitle();
        try {
            pageContext.getOut().write("<div>" + message + "</div>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
