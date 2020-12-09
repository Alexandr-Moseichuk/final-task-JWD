package by.moseichuk.final_task_JWD.bean;

public enum  UserRole {
    ADMINISTRATOR("Admin"),
    ADVERTISER("Advertiser"),
    INFLUENCER("Ifluencer"),
    MANAGER("Manager");

    private String name;

    private UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
