package by.moseichuk.adlinker.service.validator;

import by.moseichuk.adlinker.bean.Campaign;
import by.moseichuk.adlinker.service.Validator;

import java.util.Map;

public class CampaignValidator implements Validator<Campaign> {
    private static final int MIN_TITLE_LENGTH = 5;
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MIN_DESCRIPTION_LENGTH = 30;
    private static final int MAX_DESCRIPTION_LENGTH = 3000;
    private static final int MIN_REQUIREMENT_LENGTH = 30;
    private static final int MAX_REQUIREMENT_LENGTH = 3000;


    //TODO static final
    @Override
    public Map<String, String> validate(Campaign campaign) {
        int titleLength = campaign.getTitle().length();
        if (titleLength < MIN_TITLE_LENGTH || titleLength > MAX_TITLE_LENGTH) {
            errorMap.put("titleError", "Wrong length");
        }
        int descriptionLength = campaign.getDescription().length();
        if (descriptionLength < MIN_DESCRIPTION_LENGTH || descriptionLength > MAX_DESCRIPTION_LENGTH) {
            errorMap.put("descriptionError", "Wrong length");
        }
        int requirementLength = campaign.getRequirement().length();
        if (requirementLength < MIN_REQUIREMENT_LENGTH || requirementLength > MAX_REQUIREMENT_LENGTH) {
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
