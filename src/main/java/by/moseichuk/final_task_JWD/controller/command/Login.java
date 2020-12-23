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
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        System.out.println("Login " + mail + password);
        if (mail != null && password != null) {
            System.out.println("Mailand password isn't null");
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            System.out.println("after service");
            try {
                System.out.println("in try");
                User user = userService.login(mail, password);
                System.out.println("User: " + user);
                if (user != null) {
                    request.getSession().setAttribute("authorizedUser", user);
                    System.out.println("Создана сессия и успешный логин");
                    return new Forward("jsp/campaign.jsp", true);
                } else {
                    return new Forward("jsp/login.jsp", true);
                }
            } catch (ServiceException e) {
                //TODO logger
                System.out.println("Catch");
                request.getSession().setAttribute("message", "Ошибка авторизации! Свяжитесь с администратором.");
                return new Forward("jsp/error.jsp", true);
            }
        }
        return new Forward("jsp/login.jsp", true);
    }
}
