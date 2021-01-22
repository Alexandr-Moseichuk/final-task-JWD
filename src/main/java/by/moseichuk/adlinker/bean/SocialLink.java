package by.moseichuk.adlinker.bean;

public class SocialLink extends Entity {
    private Integer id;
    private Integer userId;
    private String title;
    private String link;
    private Integer views;

    public SocialLink() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
    public String toString() {
        return "SocialLink{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", views=" + views +
                '}';
    }
}
