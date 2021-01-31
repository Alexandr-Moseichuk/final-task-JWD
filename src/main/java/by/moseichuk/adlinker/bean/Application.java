package by.moseichuk.adlinker.bean;

import java.util.Calendar;
import java.util.Objects;

public class Application extends Entity {
    private Integer userId;
    private User user;
    private String comment;
    private Calendar date;

    public Application() {
    }

    public Application(Integer userId, User user, String comment, Calendar date) {
        this.userId = userId;
        this.user = user;
        this.comment = comment;
        this.date = date;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        if (!super.equals(o)) return false;
        Application that = (Application) o;
        return userId.equals(that.userId) && user.equals(that.user) && comment.equals(that.comment) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, user, comment, date);
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
