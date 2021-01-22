package by.moseichuk.adlinker.bean;

import java.util.List;

public class Manager extends User {
    private UserInfo userInfo;
    private List<Influencer> influencerList;

    public Manager() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Influencer> getInfluencerList() {
        return influencerList;
    }

    public void setInfluencerList(List<Influencer> influencerList) {
        this.influencerList = influencerList;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "userInfo=" + userInfo +
                ", influencerList=" + influencerList +
                "} " + super.toString();
    }
}
