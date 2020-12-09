package by.moseichuk.final_task_JWD.bean;

public class SocialLink {
    private String title;
    private String link;
    private Integer views;

    public SocialLink() {
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
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", views=" + views +
                '}';
    }
}
