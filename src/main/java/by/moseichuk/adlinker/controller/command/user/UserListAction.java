package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserListAction extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserListAction.class);
    private static final String RESULT_PATH = "/user/list.html";
    private static final String ERROR_JSP = "jsp/error.jsp";

    public UserListAction() {
        getPermissionSet().add(UserRole.ADMINISTRATOR);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        String[] userIdParams = request.getParameterValues("selected");
        Forward forward = new Forward(RESULT_PATH, true);

        if (action == null || userIdParams == null) {
            return forward;
        }

        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        List<Integer> userIdList = convertArrayToList(userIdParams);
        try {
            if (action.equals("block")) {
                userService.updateStatus(userIdList, UserStatus.ARCHIVE);
            } else if (action.equals("delete")) {
               for (Integer userId : userIdList) {
                   userService.delete(userId);
               }
            } else if (action.equals("unblock")) {
                userService.updateStatus(userIdList, UserStatus.VERIFIED);
            } else {
                String errorMessage = "Invalid action";
                LOGGER.error(errorMessage + " URI: " + request.getRequestURI());
                request.setAttribute("errorMessage", errorMessage);
                forward = new Forward(ERROR_JSP);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            forward = new Forward(ERROR_JSP);
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
