package com.smart.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User extends BaseDomain {
    private static final long serialVersionUID = -7471521913490203758L;

    public static final int USER_LOCK = 1;
    public static final int USER_UNLOCK = 0;
    public static final int FORUM_ADMIN = 2;
    public static final int NORMAL_USER = 1;

    private int userId;
    private String userName;
    private int userType = NORMAL_USER;
    private String lastIp;
    private Date lastVisit;
    private String password;
    private int locked;
    private int credit;
}
