package by.moseichuk.final_task_JWD.bean;

import java.util.Calendar;

public class RegistrationApplication extends User {
    private String comment;
    private Calendar date;

    public RegistrationApplication() {
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
                "comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
