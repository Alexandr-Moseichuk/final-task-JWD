package by.moseichuk.adlinker.controller.command.advertiser;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.ResultPage;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class AdvertiserVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(AdvertiserVisual.class);
    private static final String ADVERTISER_LIST_JSP = "jsp/advertiser/list.jsp";

    public AdvertiserVisual() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public ResultPage execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
        try {
            List<User> advertiserList = userService.readUsersByRole(UserRole.ADVERTISER);
            request.setAttribute("advertiserList" ,advertiserList);
            return new ResultPage(ADVERTISER_LIST_JSP);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new ResultPage(Jsp.ERROR);
        }
    }
}
