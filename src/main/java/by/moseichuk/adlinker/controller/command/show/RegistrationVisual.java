package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationVisual extends Command {
    private static final String REGISTRATION_JSP = "jsp/registration.jsp";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward(REGISTRATION_JSP);
    }
}
