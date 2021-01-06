package by.moseichuk.final_task_JWD.bean;

public class MenuItem {
    private String name;
    private String url;

    public MenuItem() {
    }

    public MenuItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
