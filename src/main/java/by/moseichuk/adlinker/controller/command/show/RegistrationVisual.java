package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationVisual extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("jsp/registration.jsp");
    }
}
