package by.moseichuk.final_task_JWD.bean;

public class UserFile extends Entity{
    private String path;

    public UserFile() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "path='" + path + '\'' +
                '}';
    }
}
