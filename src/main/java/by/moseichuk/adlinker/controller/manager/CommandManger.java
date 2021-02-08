package by.moseichuk.adlinker.controller.manager;

import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manages command execution.
 *
 * @author Moseichuk Alexandr
 */
public interface CommandManger {

    /**
     * Executes command.
     *
     * @param command  command to execute
     * @param request  http request
     * @param response http response
     * @return         forward to result page
     */
    Forward execute(Command command, HttpServletRequest request, HttpServletResponse response);

    /**
     * Shuts down manager and closes all resources.
     */
    void close();
}
