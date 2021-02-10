package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCampaignVisual extends Command {
    private static final String CREATE_JSP = "jsp/campaign/create.jsp";

    public CreateCampaignVisual() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        return new ResultPage(CREATE_JSP);
    }

}
