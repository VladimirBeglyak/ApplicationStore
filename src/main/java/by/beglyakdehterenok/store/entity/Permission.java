package by.beglyakdehterenok.store.entity;

public enum Permission {

    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write");
//    ADMIN_READ("admin:read"),
//    ADMIN_WRITE("admin:write"),
//    MANAGER_READ("manager:read"),
//    MANAGER_WRITE("manager:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
