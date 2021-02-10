package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class ApplicationUpdateVisual extends Command {
    private static final String NOT_APPROVED_JSP = "jsp/application/not_approved.jsp";

    public ApplicationUpdateVisual(){
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        return new ResultPage(NOT_APPROVED_JSP);
    }
}
