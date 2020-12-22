package by.moseichuk.final_task_JWD.controller;

import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public abstract class Command {
    private Set<UserRole> permissionSet = new HashSet<>();
    protected ServiceFactory serviceFactory;

    public Set<UserRole> getPermissionSet() {
        return permissionSet;
    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public abstract Forward execute(HttpServletRequest request, HttpServletResponse response);
}
