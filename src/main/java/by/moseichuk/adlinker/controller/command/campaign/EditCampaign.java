package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditCampaign extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditCampaign.class);
    private static final String PERMISSION_DENIED_JSP = "jsp/permission_denied.jsp";
    private static final String EDIT_JSP = "jsp/campaign/edit.jsp";
    private static final String ERROR_JSP = "jsp/error.jsp";

    public EditCampaign() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            Integer campaignId = Integer.parseInt(request.getParameter("id"));
            Integer ownerId = campaignService.readOwnerId(campaignId);
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            if (!authorizedUser.getId().equals(ownerId)) {
                return new Forward(PERMISSION_DENIED_JSP);
            }
            Campaign campaign = buildCampaign(request);
            campaignService.update(campaign);
            request.setAttribute("campaign", campaign);
            return new Forward(EDIT_JSP);
        } catch (ServiceException | NumberFormatException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }
    }

    private Campaign buildCampaign(HttpServletRequest request) {
        Campaign campaign = new Campaign();
        campaign.setId(Integer.parseInt(request.getParameter("id")));
        Calendar createDate = new GregorianCalendar();
        createDate.setTimeInMillis(Long.parseLong(request.getParameter("createDate")));
        campaign.setCreateDate(createDate);
        campaign.setBeginDate(parseDate(request.getParameter("beginDate")));
        campaign.setEndDate(parseDate(request.getParameter("endDate")));
        campaign.setTitle(request.getParameter("title"));
        campaign.setDescription(request.getParameter("description"));
        campaign.setRequirement(request.getParameter("requirement"));
        String budgetParameter = request.getParameter("budget");
        BigDecimal budget = budgetParameter.length() > 0 ? new BigDecimal(budgetParameter) : null;
        campaign.setBudget(budget);
        return campaign;
    }
    private Calendar parseDate(String date) {
        String[] splitDate = date.split("\\.");
        if (splitDate.length != 3) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));
        return calendar;
    }

}
