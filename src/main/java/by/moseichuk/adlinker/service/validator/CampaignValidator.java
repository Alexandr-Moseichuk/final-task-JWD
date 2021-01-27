package by.moseichuk.adlinker.service.validator;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.service.Validator;

import java.util.Map;

public class CampaignValidator implements Validator<Campaign> {
    @Override
    public Map<String, String> validate(Campaign campaign) {
        if (campaign.getTitle().length() < 5 || campaign.getTitle().length() > 100) {
            errorMap.put("titleError", "Wrong length");
        }
        if (campaign.getDescription().length() < 30 || campaign.getDescription().length() > 3000) {
            errorMap.put("descriptionError", "Wrong length");
        }
        if (campaign.getRequirement().length() < 30 || campaign.getRequirement().length() > 3000) {
            errorMap.put("requirementError", "Wrong length");
        }
        if (campaign.getBeginDate() == null || campaign.getEndDate() == null) {
            errorMap.put("dateError", "Wrong date");
        }
        if (campaign.getBudget() == null) {
            errorMap.put("budgetError", "Wrong budget");
        }
        return errorMap;
    }
}
