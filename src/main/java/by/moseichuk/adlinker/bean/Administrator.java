package by.moseichuk.adlinker.bean;

import java.util.List;

public class Administrator extends User {
    private List<Application> applicationList;

    public Administrator() {
    }

    public List<Application> getRegistrationApplicationList() {
        return applicationList;
    }

    public void setRegistrationApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "registrationApplicationList=" + applicationList +
                "} " + super.toString();
    }
}
