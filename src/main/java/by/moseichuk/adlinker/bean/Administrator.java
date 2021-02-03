package by.moseichuk.adlinker.bean;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(applicationList, that.applicationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), applicationList);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "registrationApplicationList=" + applicationList +
                "} " + super.toString();
    }
}
