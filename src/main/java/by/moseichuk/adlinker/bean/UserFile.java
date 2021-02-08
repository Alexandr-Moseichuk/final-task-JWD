package by.moseichuk.adlinker.bean;

import java.util.Objects;

public class UserFile extends Entity{
    private String path;

    public UserFile() {
    }

    public UserFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFile)) return false;
        if (!super.equals(o)) return false;
        UserFile userFile = (UserFile) o;
        return Objects.equals(path, userFile.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), path);
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "path='" + path + '\'' +
                '}';
    }
}
