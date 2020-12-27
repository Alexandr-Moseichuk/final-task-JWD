package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.CampaignService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CampaignListCommand extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);
        try {
            List<Campaign> campaignList = campaignService.readAll();
            request.setAttribute("campaignList", campaignList);
            return new Forward("jsp/campaign/list.jsp");
        } catch (ServiceException e) {
            //TODO logger
            request.getSession().setAttribute("errorMessage", "Ошибка получения кампаний из БД!");
            return new Forward("jsp/error.jsp");
        }
    }
}
