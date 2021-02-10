package by.moseichuk.adlinker.controller.command.show;

import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationVisual extends Command {
    private static final String REGISTRATION_JSP = "jsp/registration.jsp";

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        return new ResultPage(REGISTRATION_JSP);
    }
}
