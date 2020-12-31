package by.moseichuk.final_task_JWD.controller.command.show;

import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexVisual extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("jsp/index.jsp");
    }
}
