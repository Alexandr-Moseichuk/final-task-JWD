package by.moseichuk.adlinker.bean;

import java.util.Calendar;
import java.util.Objects;

public class User extends Entity {
    private String email;
    private String password;
    private UserRole role;
    private Calendar registrationDate;
    private UserStatus status;
    private UserInfo userInfo;

    public User() {

    }

    public User(User user) {
        setId(user.getId());
        email = user.getEmail();
        password = user.getPassword();
        role = user.getRole();
        registrationDate = user.getRegistrationDate();
        status = user.getStatus();
        userInfo = user.getUserInfo();
    }

    public User(Integer id, String email, String password, UserRole role, Calendar registrationDate, UserStatus status, UserInfo userInfo) {
        setId(id);
        this.email = email;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
        this.status = status;
        this.userInfo = userInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return email.equals(user.email) && password.equals(user.password)
                && role == user.role && registrationDate.equals(user.registrationDate)
                && status == user.status && Objects.equals(userInfo, user.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password, role, registrationDate, status, userInfo);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                ", userInfo=" + userInfo +
                '}';
    }
}
