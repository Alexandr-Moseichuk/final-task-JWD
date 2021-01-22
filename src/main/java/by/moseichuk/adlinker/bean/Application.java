package by.moseichuk.adlinker.bean;

import java.util.Calendar;

public class Application extends Entity {
    private Integer userId;
    private User user;
    private String comment;
    private Calendar date;

    public Application() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RegistrationApplication{" +
                "userId=" + userId +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}