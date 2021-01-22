package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.Command;
import by.moseichuk.adlinker.controller.Forward;
import by.moseichuk.adlinker.service.ApplicationService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ApplicationListAction extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationListAction.class);

    public ApplicationListAction() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        String[] userIdArray = request.getParameterValues("selected");
        Forward forward = new Forward("/application/list", true);

        if (action == null || userIdArray == null) {
            //TODO add message
            return forward;
        }

        ApplicationService service =
                (ApplicationService) serviceFactory.getService(ServiceEnum.APPLICATION);

        try {
            if (action.equals("approve")) {
                service.approveByIds(convertArrayToList(userIdArray));
            } else if (action.equals("reject")) {
                service.rejectByIds(convertArrayToList(userIdArray));
            } else {
                String errorMessage = "Invalid action";
                LOGGER.error(errorMessage + " URI: " + request.getRequestURI());
                request.setAttribute("errorMessage", errorMessage);
                forward = new Forward("jsp/error.jsp");
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            forward = new Forward("jsp/error.jsp");
        }

        return forward;
    }

    private List<Integer> convertArrayToList(String[] userIdArray) {
        List<Integer> integerList = new ArrayList<>();
        for (String s : userIdArray) {
            try {
                integerList.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                LOGGER.error(e);
            }
        }
        return integerList;
    }
}
