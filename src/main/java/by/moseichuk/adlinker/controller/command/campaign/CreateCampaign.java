package by.moseichuk.adlinker.controller.command.campaign;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.CampaignService;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.Validator;
import by.moseichuk.adlinker.service.ValidatorFactory;
import by.moseichuk.adlinker.service.exception.ServiceException;
import by.moseichuk.adlinker.service.validator.ValidatorEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class CreateCampaign extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateCampaign.class);

    public CreateCampaign() {
        getPermissionSet().add(UserRole.ADVERTISER);
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Campaign campaign = buildCampaign(request);

        Validator<Campaign> campaignValidator = ValidatorFactory.getValidator(ValidatorEnum.CAMPAIGN);
        Map<String, String> errorMap = campaignValidator.validate(campaign);
        if (errorMap.size() > 0) {
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            LOGGER.debug(errorMap);
            return new Forward("jsp/campaign/create.jsp");
        }

        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            campaignService.add(campaign);
            return new Forward("/campaign/list", true);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward("jsp/error.jsp");
        }
    }

    private Campaign buildCampaign(HttpServletRequest request) {
        Campaign campaign = new Campaign();
        campaign.setTitle(request.getParameter("title"));
        campaign.setDescription(request.getParameter("description"));
        campaign.setRequirement(request.getParameter("requirement"));
        campaign.setBeginDate(parseDate(request.getParameter("beginDate")));
        campaign.setEndDate(parseDate(request.getParameter("endDate")));
        campaign.setCreateDate(new GregorianCalendar());
        String budgetParameter = request.getParameter("budget");
        BigDecimal budget = budgetParameter.length() > 0 ? new BigDecimal(budgetParameter) : null;
        campaign.setBudget(budget);
        //TODO user files
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
