package by.moseichuk.adlinker.controller.command;

import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains actions which user can execute if has access.
 *
 * @author Alexandr Moseichuk
 */
public abstract class Command {
    /**
     * Set of user role that can execute command
     */
    private final Set<UserRole> permissionSet = new HashSet<>();
    /**
     * Factory for creating services implementations
     */
    protected ServiceFactory serviceFactory;
    /**
     * Name of command
     */
    private String name;

    /**
     * Returns set of allowed roles
     *
     * @return set of allowed roles
     */
    public Set<UserRole> getPermissionSet() {
        return permissionSet;
    }

    /**
     * Sets service factory
     *
     * @param serviceFactory new service factory
     */
    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    /**
     * Returns command name
     *
     * @return command name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets command name
     *
     * @param name command name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Executes user command
     *
     * @param request  http request
     * @param response http response
     * @return {@code Forward} witch leads to result page
     */
    public abstract ResultPage execute(HttpServletRequest request, HttpServletResponse response);

    @Override
    public String toString() {
        return "Command{" +
                "permissionSet=" + permissionSet +
                ", name='" + name + '\'' +
                '}';
    }
}
