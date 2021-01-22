package by.moseichuk.adlinker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManger {

    Forward execute(Command command, HttpServletRequest request, HttpServletResponse response);

    void close();
}
