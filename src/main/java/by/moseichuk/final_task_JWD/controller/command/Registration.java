package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.*;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.RegistrationApplicationService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;

public class Registration extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        int role = Integer.parseInt(request.getParameter("userRole"));

        if (email.isEmpty() || password.isEmpty() || passwordCheck.isEmpty() || !password.equals(passwordCheck)) {
            request.setAttribute("registrationError", "Проерте почту и пароль");
            return new Forward("jsp/registration.jsp");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        Calendar currentTime = new GregorianCalendar();
        currentTime.setTimeInMillis(System.currentTimeMillis());
        user.setRegistrationDate(currentTime);
        user.setRole(UserRole.values()[role]);
        user.setStatus(UserStatus.UNVERIFIED);

        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        RegistrationApplicationService applicationService = (RegistrationApplicationService) serviceFactory.getService(ServiceEnum.REGISTRATION_APPLICATION);
        try {
            Integer userId = userService.create(user);
            UserInfo userInfo = buildUserInfo(request, userId);
            userService.createUserInfo(userInfo);

            RegistrationApplication application = buildApplication(request, userId, currentTime);
            applicationService.add(application);
            return new Forward("/campaign/list", true);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка регистрации пользователя");
            return new Forward("jsp/error.jsp");
        }

    }

    private UserInfo buildUserInfo(HttpServletRequest request, Integer userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setLastName(request.getParameter("lastName"));
        userInfo.setFirstName(request.getParameter("firstName"));
        userInfo.setSecondName(request.getParameter("secondName"));
        userInfo.setDescription(request.getParameter("description"));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));

        UserFile userFile = new UserFile();
        userFile.setId(1);
        userFile.setPath("somePath");

        userInfo.setUserFile(userFile);
        return userInfo;
    }

    private RegistrationApplication buildApplication(HttpServletRequest request, Integer userId, Calendar currentTime) {
        RegistrationApplication application = new RegistrationApplication();
        application.setUserId(userId);
        application.setComment(request.getParameter("comment"));
        application.setDate(currentTime);
        return application;
    }
}
