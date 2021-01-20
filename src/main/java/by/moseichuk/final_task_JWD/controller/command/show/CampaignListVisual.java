package by.moseichuk.final_task_JWD.controller.command.show;

import by.moseichuk.final_task_JWD.bean.Campaign;
import by.moseichuk.final_task_JWD.bean.UserRole;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import by.moseichuk.final_task_JWD.service.CampaignService;
import by.moseichuk.final_task_JWD.service.ServiceEnum;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class CampaignListVisual extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CampaignListVisual.class);
    // сколько ссылок отображается начиная с самой первой (не может быть установлено в 0)
    final int N_PAGES_FIRST = 1;
    // сколько ссылок отображается слева от текущей (может быть установлено в 0)
    final int N_PAGES_PREV = 1;
    // сколько ссылок отображается справа от текущей (может быть установлено в 0)
    final int N_PAGES_NEXT = 1;
    // сколько ссылок отображается в конце списка страниц (не может быть установлено в 0)
    final int N_PAGES_LAST = 1;

    public CampaignListVisual() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        CampaignService campaignService = (CampaignService) serviceFactory.getService(ServiceEnum.CAMPAIGN);

        String currentPageParameter =  request.getParameter("currentPage");
        LOGGER.debug("Current page: " + currentPageParameter);
        //Integer pageSize = (Integer) request.getAttribute("pageSize");
        int pageSize = 1;
        int currentPage = 1;
        if (currentPageParameter != null) {
            currentPage = Integer.parseInt(currentPageParameter);

        }

        try {
            int totalRecords = campaignService.readRowCount();
            LOGGER.debug("Total records: " + totalRecords);
            int offset = pageSize * (currentPage - 1);
            List<Campaign> pageCampaignList = campaignService.readSublist(pageSize, offset);
            LOGGER.debug("List: " + pageCampaignList.size());
            request.setAttribute("campaignSubList", pageCampaignList);
            int pages = totalRecords / pageSize;
            int lastPage = pages * pageSize < totalRecords ? pages + 1 : pages;
            request.setAttribute("lastPage", lastPage);
            LOGGER.debug("last page: " + lastPage);
            // показывать ли полностью все ссылки на страницы слева от текущей, или вставить многоточие
            boolean showAllPrev;
            // показывать ли полностью все ссылки на страницы справа от текущей, или вставить многоточие
            boolean showAllNext;
            showAllPrev = N_PAGES_FIRST >= (currentPage - N_PAGES_PREV);
            showAllNext = currentPage + N_PAGES_NEXT >= lastPage - N_PAGES_LAST;
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("N_PAGES_FIRST", N_PAGES_FIRST );
            request.setAttribute("N_PAGES_PREV", N_PAGES_PREV );
            request.setAttribute("N_PAGES_NEXT", N_PAGES_NEXT );
            request.setAttribute("N_PAGES_LAST", N_PAGES_LAST );
            request.setAttribute("showAllPrev", showAllPrev );
            request.setAttribute("showAllNext", showAllNext );
            return new Forward("jsp/campaign/list.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Ошибка получения кампаний");
            return new Forward("jsp/error.jsp");
        }
    }
}
