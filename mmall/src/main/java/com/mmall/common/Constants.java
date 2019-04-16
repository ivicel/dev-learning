package com.mmall.common;

public class Constants {
    public static final String CURRENT_USER = "current_user";

    public static final String CHECK_TYPE_UESRNAME = "username";

    public static final String CHECK_TYPE_EMAIL = "email";

    public static class Role {
        public static final Integer ROLE_ADMIN = 0;

        public static final Integer ROLE_USER = 1;
    }

    public interface Cart {
        int CHECKED = 1;
        int UNCHECKED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

}
