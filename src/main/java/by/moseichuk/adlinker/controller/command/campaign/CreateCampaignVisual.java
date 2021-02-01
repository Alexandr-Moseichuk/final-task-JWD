package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCampaignVisual extends Command {

    public CreateCampaignVisual() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("jsp/campaign/create.jsp");
    }

}
