package by.moseichuk.adlinker.bean;

import java.util.Objects;

public class SocialLink extends Entity {
    private Integer userId;
    private String title;
    private String link;
    private Integer views;

    public SocialLink() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialLink)) return false;
        if (!super.equals(o)) return false;
        SocialLink that = (SocialLink) o;
        return Objects.equals(userId, that.userId) && Objects.equals(title, that.title) && Objects.equals(link, that.link) && Objects.equals(views, that.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, title, link, views);
    }

    @Override
    public String toString() {
        return "SocialLink{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", views=" + views +
                "} " + super.toString();
    }
}
