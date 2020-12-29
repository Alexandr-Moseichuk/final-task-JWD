package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(mail + " " + password);
        if (mail != null && password != null) {
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            try {
                User user = userService.login(mail, password);
                if (user != null) {
                    request.getSession().setAttribute("authorizedUser", user);
                    return new Forward("/campaign/list", true);
                } else {
                    return new Forward("jsp/login.jsp");
                }
            } catch (ServiceException e) {
                //TODO logger
                request.getSession().setAttribute("errorMessage", "Ошибка авторизации! Свяжитесь с администратором.");
                return new Forward("jsp/error.jsp");
            }
        }
        return new Forward("jsp/login.jsp");
    }
}
