package by.moseichuk.adlinker.bean;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertiser)) return false;
        if (!super.equals(o)) return false;
        Advertiser that = (Advertiser) o;
        return Objects.equals(userInfo, that.userInfo) && Objects.equals(campaignList, that.campaignList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userInfo, campaignList);
    }

    @Override
    public String toString() {
        return "Advertiser{" +
                "userInfo=" + userInfo +
                ", campaignList=" + campaignList +
                "} " + super.toString();
    }
}
