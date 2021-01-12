package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.*;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.UserService;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        LOGGER.debug(request.getParameter("userRole"));
        int role = Integer.parseInt(request.getParameter("userRole"));
        String description = request.getParameter("description");
        String phoneNumber = request.getParameter("phoneNumber");
        if (email.isEmpty() || password.isEmpty() || passwordCheck.isEmpty() || !password.equals(passwordCheck)) {
            return new Forward("jsp/registration.jsp");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(md5(password));
        Calendar currentTime = new GregorianCalendar();
        currentTime.setTimeInMillis(System.currentTimeMillis());
        user.setRegistrationDate(currentTime);
        user.setRole(UserRole.values()[role]);
        user.setStatus(UserStatus.UNVERIFIED);
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
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка регистрации пользователя");
            return new Forward("jsp/error.jsp");
        }

    }

    private String md5(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(password.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for(int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5summ = formatter.toString();
            formatter.close();
            return md5summ;
        } catch(NoSuchAlgorithmException e) {
            return null;
        }
    }
}
