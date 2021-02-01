package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public abstract class Command {
    private Set<UserRole> permissionSet = new HashSet<>();
    protected ServiceFactory serviceFactory;
    private String name;

    public Set<UserRole> getPermissionSet() {
        return permissionSet;
    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Forward execute(HttpServletRequest request, HttpServletResponse response);

    @Override
    public String toString() {
        return "Command{" +
                "permissionSet=" + permissionSet +
                ", name='" + name + '\'' +
                '}';
    }
}
