package com.smart.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post extends BaseDomain {
    private static final long serialVersionUID = 3879977062561462196L;

    private int postId;
    private String postTitle;
    private String postText;
    private int boardId;
    private Date createTime;
    private User user;
    private Topic topic;
}
