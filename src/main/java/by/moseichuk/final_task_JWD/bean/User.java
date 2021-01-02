package by.moseichuk.final_task_JWD.bean;

import java.util.Calendar;

public  class User extends Entity {
    private String email;
    private String password;
    private UserRole role;
    private Calendar registrationDate;
    private UserStatus status;

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

    @Override
    public String toString() {
        return "User{" +
                "mail='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                '}';
    }
}
