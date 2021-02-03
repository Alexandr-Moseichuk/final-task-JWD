package by.moseichuk.adlinker.bean;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name) && Objects.equals(url, menuItem.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
