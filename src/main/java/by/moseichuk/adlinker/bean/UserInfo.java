package by.moseichuk.adlinker.bean;

import java.util.Objects;

public class UserInfo extends Entity {
    private Integer userId;
    private String lastName;
    private String firstName;
    private String secondName;
    private String description;
    private String phoneNumber;
    private UserFile userFile;

    public UserInfo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserFile getUserFile() {
        return userFile;
    }

    public void setUserFile(UserFile userFile) {
        this.userFile = userFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        if (!super.equals(o)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userId, userInfo.userId) && Objects.equals(lastName, userInfo.lastName) && Objects.equals(firstName, userInfo.firstName) && Objects.equals(secondName, userInfo.secondName) && Objects.equals(description, userInfo.description) && Objects.equals(phoneNumber, userInfo.phoneNumber) && Objects.equals(userFile, userInfo.userFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, lastName, firstName, secondName, description, phoneNumber, userFile);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userFile=" + userFile +
                '}';
    }
}
