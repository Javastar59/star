package com.star.service.key;

public final class CommonKey {

    private static final String USER_PREFIX = "user::";

    public static String getUserPrefix(Long id) {
        return String.format("%s%s", USER_PREFIX, String.valueOf(id));
    }
}
