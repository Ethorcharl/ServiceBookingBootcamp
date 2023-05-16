package com.project.ServiceBooking.data;

public enum Permission {
    CLIENT_READ("client:read"),
    SPECIALIST_WRITE("specialist:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}