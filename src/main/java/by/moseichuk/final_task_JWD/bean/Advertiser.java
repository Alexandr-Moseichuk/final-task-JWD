package by.moseichuk.final_task_JWD.bean;

import java.util.List;

public class Advertiser extends User {
    private UserInfo userInfo;
    private List<Campaign> campaignList;

    public Advertiser() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    @Override
    public String toString() {
        return "Advertiser{" +
                "userInfo=" + userInfo +
                ", campaignList=" + campaignList +
                "} " + super.toString();
    }
}
