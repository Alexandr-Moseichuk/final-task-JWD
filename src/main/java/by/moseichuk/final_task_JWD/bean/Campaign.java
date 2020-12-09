package by.moseichuk.final_task_JWD.bean;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class Campaign extends Entity {
    private String title;
    private Calendar createDate;
    private Calendar beginDate;
    private Calendar endDate;
    private String description;
    private String requirement;
    private BigDecimal budget;
    private List<UserFile> userFileList;
    private List<Influencer> influencerList;

    public Campaign() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public List<UserFile> getUserFileList() {
        return userFileList;
    }

    public void setUserFileList(List<UserFile> userFileList) {
        this.userFileList = userFileList;
    }

    public List<Influencer> getInfluencerList() {
        return influencerList;
    }

    public void setInfluencerList(List<Influencer> influencerList) {
        this.influencerList = influencerList;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "title='" + title + '\'' +
                ", createDate=" + createDate +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", requirement='" + requirement + '\'' +
                ", budget=" + budget +
                ", userFileList=" + userFileList +
                ", influencerList=" + influencerList +
                "} " + super.toString();
    }
}
