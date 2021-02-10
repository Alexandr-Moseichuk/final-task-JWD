package by.moseichuk.adlinker.controller.command.application;

import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
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
    private static final String RESULT_PATH = "/application/list.html";

    public ApplicationListAction() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        String[] userIdArray = request.getParameterValues("selected");
        ResultPage resultPage = new ResultPage(RESULT_PATH, true);

        if (action == null || userIdArray == null) {
            return resultPage;
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
                request.setAttribute(Attribute.ERROR_MESSAGE, errorMessage);
                resultPage = new ResultPage(Jsp.ERROR);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            resultPage = new ResultPage(Jsp.ERROR);
        }

        return resultPage;
    }

    private List<Integer> convertArrayToList(String[] userIdArray) {
        List<Integer> integerList = new ArrayList<>();
        for (String s : userIdArray) {
            try {
                integerList.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return integerList;
    }
}
