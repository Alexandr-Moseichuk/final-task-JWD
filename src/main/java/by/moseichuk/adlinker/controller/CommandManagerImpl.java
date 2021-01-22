package by.moseichuk.adlinker.controller;

import by.moseichuk.adlinker.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManagerImpl implements CommandManger {
    private ServiceFactory serviceFactory;

    public CommandManagerImpl(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public Forward execute(Command command, HttpServletRequest request, HttpServletResponse response) {
        command.setServiceFactory(serviceFactory);
        return command.execute(request, response);
    }

    @Override
    public void close() {
        serviceFactory.close();
    }
}
