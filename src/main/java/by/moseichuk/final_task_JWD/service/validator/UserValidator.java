package by.moseichuk.final_task_JWD.service.validator;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.service.Validator;
import by.moseichuk.final_task_JWD.service.exception.ValidationException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    /**
     * Secure Password requirements
     *
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password must contain at least one special character like ! @ # & ( ).
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.
     * */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public Map<String, String> validate(User user) {
        Map<String, String> errorMap = new HashMap<>();
        String email = user.getEmail();
        if (email == null || email.isEmpty() || !isValidEmail(email)) {
            errorMap.put("emailError", "emailError");
        }
        String password = user.getPassword();
        if (!isValidPassword(password)) {
            errorMap.put("passwordError", "passwordError");
        }
        return errorMap;
    }

    private boolean isValidEmail(String email) {
        try {
            InternetAddress address = new InternetAddress(email, true);
            address.validate();
        } catch (AddressException e) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}
