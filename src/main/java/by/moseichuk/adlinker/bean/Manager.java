package by.moseichuk.adlinker.bean;

import java.util.List;

public class Manager extends User {
    private List<Influencer> influencerList;

    public Manager() {
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
                "influencerList=" + influencerList +
                '}';
    }
}
