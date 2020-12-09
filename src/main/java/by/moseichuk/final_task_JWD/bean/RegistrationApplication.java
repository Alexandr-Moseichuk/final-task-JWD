package by.moseichuk.final_task_JWD.bean;

import java.util.Calendar;

public class RegistrationApplication extends User {
    private Calendar applicationDate;
    private String comment;
    private String mobilePhone;

    public RegistrationApplication() {
    }

    public Calendar getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Calendar applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "RegistrationApplication{" +
                "applicationDate=" + applicationDate +
                ", comment='" + comment + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                "} " + super.toString();
    }
}
