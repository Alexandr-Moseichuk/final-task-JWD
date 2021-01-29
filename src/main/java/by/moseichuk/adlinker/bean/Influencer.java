package by.moseichuk.adlinker.bean;

import java.util.Calendar;
import java.util.List;

public class Influencer extends User {
    private UserInfo userInfo;
    private Manager manager;
    private Calendar managerBeginDate;
    private List<SocialLink> linkList;
    private List<Campaign> campaignList;

    public Influencer() {
    }

    public Influencer(User user) {
        super(user);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Calendar getManagerBeginDate() {
        return managerBeginDate;
    }

    public void setManagerBeginDate(Calendar managerBeginDate) {
        this.managerBeginDate = managerBeginDate;
    }

    public List<SocialLink> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<SocialLink> linkList) {
        this.linkList = linkList;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    @Override
    public String toString() {
        return "Influencer{" +
                "userInfo=" + userInfo +
                ", manager=" + manager +
                ", managerBeginDate=" + managerBeginDate +
                ", linkList=" + linkList +
                ", campaignList=" + campaignList +
                '}';
    }
}
