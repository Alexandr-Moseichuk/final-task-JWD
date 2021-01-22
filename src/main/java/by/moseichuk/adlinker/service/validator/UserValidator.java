package by.moseichuk.adlinker.service.validator;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserInfo;
import by.moseichuk.adlinker.service.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final String PHONE_REGEX =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    @Override
    public Map<String, String> validate(User user) {
        String email = user.getEmail();
        if (email == null || email.isEmpty() || !isValidEmail(email)) {
            errorMap.put("emailError", "emailError");
        }
        String password = user.getPassword();
        if (!isValidPassword(password)) {
            errorMap.put("passwordError", "passwordError");
        }

        UserInfo userInfo = user.getUserInfo();
        String lastName = userInfo.getLastName();
        if (lastName == null || lastName.isEmpty() || lastName.length() < 2) {
            errorMap.put("lastNameError", "lastNameError");
        }
        String firstName = userInfo.getFirstName();
        if (firstName == null || firstName.isEmpty() || firstName.length() < 2) {
            errorMap.put("firstNameError", "firstNameError");
        }
        String description = userInfo.getDescription();
        if (description == null || description.isEmpty() || description.length() < 50) {
            errorMap.put("descriptionError", "descriptionError");
        }
        String phoneNumber = userInfo.getPhoneNumber();
        if (!isValidPhoneNumber(phoneNumber)) {
            errorMap.put("phoneNumberError", "descriptionError");
        }
        return errorMap;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
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
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}