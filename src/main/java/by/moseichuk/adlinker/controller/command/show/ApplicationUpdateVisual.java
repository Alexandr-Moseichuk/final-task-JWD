package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class ApplicationUpdateVisual extends Command {

    public ApplicationUpdateVisual(){
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("jsp/application/update");
    }
}
