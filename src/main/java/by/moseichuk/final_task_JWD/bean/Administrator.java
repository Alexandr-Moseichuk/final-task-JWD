package by.moseichuk.final_task_JWD.bean;

import java.util.List;

public class Administrator extends User {
    private List<RegistrationApplication> registrationApplicationList;

    public Administrator() {
    }

    public List<RegistrationApplication> getRegistrationApplicationList() {
        return registrationApplicationList;
    }

    public void setRegistrationApplicationList(List<RegistrationApplication> registrationApplicationList) {
        this.registrationApplicationList = registrationApplicationList;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "registrationApplicationList=" + registrationApplicationList +
                "} " + super.toString();
    }
}
