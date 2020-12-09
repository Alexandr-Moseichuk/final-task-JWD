package by.moseichuk.final_task_JWD.bean;

public class UserFile extends Entity{
    private String path;
    private String name;
    private String fileType;

    public UserFile() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", fileType='" + fileType + '\'' +
                "} " + super.toString();
    }

}
