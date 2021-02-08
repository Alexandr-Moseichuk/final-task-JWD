package by.moseichuk.adlinker.controller.manager;

import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manages command execution. Using service factory to access service layer.
 *
 * @author Moseichuk Alexadnr
 */
public class CommandManagerImpl implements CommandManger {
    private final ServiceFactory serviceFactory;

    /**
     * Creates new {@code CommandManagerImpl}. Sets service factory.
     *
     * @param serviceFactory factory to access service layer
     */
    public CommandManagerImpl(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    /**
     * Sets service factory to command and starts execution
     *
     * @param command  command to execute
     * @param request  http request
     * @param response http response
     * @return         forward to result page
     */
    @Override
    public Forward execute(Command command, HttpServletRequest request, HttpServletResponse response) {
        command.setServiceFactory(serviceFactory);
        return command.execute(request, response);
    }

    /**
     * Closes service factory
     */
    @Override
    public void close() {
        serviceFactory.close();
    }
}
