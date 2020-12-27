package by.moseichuk.final_task_JWD.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManger {

    Forward execute(Command command, HttpServletRequest request, HttpServletResponse response);

    void close();
}
