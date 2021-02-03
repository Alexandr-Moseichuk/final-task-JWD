package by.moseichuk.adlinker.bean;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(influencerList, manager.influencerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), influencerList);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "influencerList=" + influencerList +
                '}';
    }
}
