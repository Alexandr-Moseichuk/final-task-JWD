package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.controller.servlet.Forward;
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

/**
 * Creating new user, user info and registration application after passing validation.
 *
 * @author Moseichuk Alexandr
 */
public class Registration extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String REGISTRATION_JSP = "jsp/registration.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";
    private static final String REDIRECT_PATH = "/campaign/list.html";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String PASSWORD_CHECK_ATTRIBUTE = "passwordCheckError";
    private static final String REGISTRATION_ERROR = "registration.feedback.error";

    /**
     * Validates params from registration form and creates new user with registration application
     *
     * @param request  http request
     * @param response http response
     * @return         forward to result page or error page
     */
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
            return new Forward(REGISTRATION_JSP);
        }

        String passwordCheck = request.getParameter("passwordCheck");
        if (!user.getPassword().equals(passwordCheck)) {
            request.setAttribute(PASSWORD_CHECK_ATTRIBUTE, REGISTRATION_ERROR);
            return new Forward(REGISTRATION_JSP);
        }

        Application application = buildApplication(request, user.getRegistrationDate());
        Validator<Application> applicationValidator = ValidatorFactory.getValidator(ValidatorEnum.APPLICATION);
        Map<String, String> applicationErrorMap = applicationValidator.validate(application);
        if (!userErrorMap.isEmpty()) {
            for (Map.Entry<String, String> entry : applicationErrorMap.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            return new Forward(REGISTRATION_JSP);
        }

        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            userService.register(user, userInfo, application);
            return new Forward(REDIRECT_PATH, true);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
            return new Forward(ERROR_JSP);
        }

    }

    /**
     * Builds user by params from registration form
     *
     * @param request http request
     * @return        user object
     */
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

    /**
     * Builds user info by params from registration form
     *
     * @param request httm request
     * @return        user info object
     */
    private UserInfo buildUserInfo(HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        
        userInfo.setLastName(request.getParameter("lastName"));
        userInfo.setFirstName(request.getParameter("firstName"));
        userInfo.setSecondName(request.getParameter("secondName"));
        userInfo.setDescription(request.getParameter("description"));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));
        UserFile userFile = new UserFile();
        userFile.setPath("");

        userInfo.setUserFile(userFile);
        return userInfo;
    }

    /**
     * Builds registration application by params from registration form
     *
     * @param request     http request
     * @param currentTime current time
     * @return            application object
     */
    private Application buildApplication(HttpServletRequest request, Calendar currentTime) {
        Application application = new Application();
        application.setComment(request.getParameter("comment"));
        application.setDate(currentTime);
        return application;
    }
}
