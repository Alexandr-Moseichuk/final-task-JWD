package by.moseichuk.adlinker.constant;

public enum  UserRole {
    ADMINISTRATOR("Admin"),
    ADVERTISER("Advertiser"),
    INFLUENCER("Ifluencer"),
    MANAGER("Manager");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
