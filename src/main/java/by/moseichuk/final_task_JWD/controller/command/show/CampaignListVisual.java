package by.moseichuk.final_task_JWD.controller.command.show;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.CampaignService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CampaignListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CampaignListVisual.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            List<Campaign> campaignList = campaignService.readAll();
            request.setAttribute("campaignList", campaignList);
            return new Forward("jsp/campaign/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", "Ошибка получения кампаний");
            return new Forward("jsp/error.jsp");
        }
    }
}
