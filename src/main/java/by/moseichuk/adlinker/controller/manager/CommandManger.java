package by.moseichuk.adlinker.controller.manager;

import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManger {

    Forward execute(Command command, HttpServletRequest request, HttpServletResponse response);

    void close();
}
