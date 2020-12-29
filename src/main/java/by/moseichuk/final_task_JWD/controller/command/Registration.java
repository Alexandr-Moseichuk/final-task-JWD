package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registration extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Forward forward = null;

        if (session != null) {
            User authorizedUser = (User) session.getAttribute("authorizedUser");
            if (authorizedUser != null) {
                forward = new Forward("/campaign/list", true);
            } else {
                forward = new Forward("jsp/registration.jsp");
            }
        } else {
            forward = new Forward("jsp/registration.jsp");
        }
        return forward;
    }
}
