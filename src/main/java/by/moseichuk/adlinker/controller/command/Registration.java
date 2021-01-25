package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
import by.moseichuk.adlinker.service.*;
import by.moseichuk.adlinker.service.exception.ServiceException;
import by.moseichuk.adlinker.service.validator.ValidatorEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class Registration extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        User user = buildUser(request);
        UserInfo userInfo = buildUserInfo(request);
        user.setUserInfo(userInfo);

        Validator<User> userValidator = ValidatorFactory.getValidator(ValidatorEnum.USER);
        Map<String, String> userErrorMap = userValidator.validate(user);
        if (!userErrorMap.isEmpty()) {
            for (Map.Entry<String, String> entry : userErrorMap.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return new Forward("jsp/registration.jsp");
        }

        String passwordCheck = request.getParameter("passwordCheck");
        if (!user.getPassword().equals(passwordCheck)) {
            request.setAttribute("passwordCheckError", "Пароли не совпадает");
            return new Forward("jsp/registration.jsp");
        }

        Application application = buildApplication(request, user.getRegistrationDate());
        Validator<Application> applicationValidator = ValidatorFactory.getValidator(ValidatorEnum.APPLICATION);
        Map<String, String> applicationErrorMap = applicationValidator.validate(application);
        if (!userErrorMap.isEmpty()) {
            for (Map.Entry<String, String> entry : applicationErrorMap.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return new Forward("jsp/registration.jsp");
        }

        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        UserInfoService userInfoService = (UserInfoService) serviceFactory.getService(ServiceEnum.USER_INFO);
        ApplicationService applicationService = (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);
        try {
            Integer userId = userService.create(user);
            userInfo.setUserId(userId);
            userInfoService.create(userInfo);
            application.setUserId(userId);
            applicationService.add(application);
            return new Forward("/campaign/list", true);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка регистрации пользователя");
            return new Forward("jsp/error.jsp");
        }

    }

    private User buildUser(HttpServletRequest request) {
        User user = new User();

        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        Calendar currentTime = new GregorianCalendar();
        currentTime.setTimeInMillis(System.currentTimeMillis());
        user.setRegistrationDate(currentTime);
        int role = Integer.parseInt(request.getParameter("userRole"));
        user.setRole(UserRole.values()[role]);
        user.setStatus(UserStatus.UNVERIFIED);
        return user;
    }

    private UserInfo buildUserInfo(HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        
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

    private Application buildApplication(HttpServletRequest request, Calendar currentTime) {
        Application application = new Application();
        application.setComment(request.getParameter("comment"));
        application.setDate(currentTime);
        return application;
    }
}
