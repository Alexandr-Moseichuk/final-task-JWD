package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.*;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Registration extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equalsIgnoreCase("get")) {
            return new Forward("jsp/registration.jsp");
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String description = request.getParameter("description");
        String phoneNumber = request.getParameter("phoneNumber");
        if (email.isEmpty() || password.isEmpty() || passwordCheck.isEmpty() || !password.equals(passwordCheck)) {
            return new Forward("jsp/registration.jsp");
        }

        User user = new User();
        user.setMail(email);
        user.setPassword(password);
        Calendar currentTime = new GregorianCalendar();
        currentTime.setTimeInMillis(System.currentTimeMillis());
        user.setRegistrationDate(currentTime);
        user.setRole(UserRole.ADMINISTRATOR);
        user.setStatus(UserStatus.VERIFIED);
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            Integer userId = userService.create(user);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setLastName(lastName);
            userInfo.setFirstName(firstName);
            userInfo.setSecondName(secondName);
            userInfo.setDescription(description);
            userInfo.setPhoneNumber(phoneNumber);
            UserFile userFile = new UserFile();
            userFile.setId(1);
            userFile.setPath("somePath");
            userInfo.setUserFile(userFile);
            userService.createUserInfo(userInfo);

            return new Forward("/campaign/list", true);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward("jsp/error.jsp");
        }

    }
}
