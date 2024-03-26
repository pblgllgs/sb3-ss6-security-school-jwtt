package com.pblgllgs.sb3ss6securityschool.enums;

public enum UserType {
    ADMIN("ADMIN"), TEACHER("TEACHER"), STUDENT("STUDENT");

    private final String type;

    UserType(String string) {
        type = string;
    }

    @Override
    public String toString() {
        return type;
    }
}
