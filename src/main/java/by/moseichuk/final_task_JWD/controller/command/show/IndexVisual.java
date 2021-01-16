package by.moseichuk.final_task_JWD.controller.command.show;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class IndexVisual extends Command {

    public IndexVisual() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new Forward("jsp/index.jsp");
        } else {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser == null) {
                return new Forward("jsp/index.jsp");
            } else {
                return new Forward("/campaign/list", true);
            }

        }
    }
}
