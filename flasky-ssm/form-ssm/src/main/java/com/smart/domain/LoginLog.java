package com.smart.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginLog extends BaseDomain {
    private static final long serialVersionUID = 5274963093503898825L;

    private int loginId;
    private Date loginDate;
    private User user;
    private String ip;
}
