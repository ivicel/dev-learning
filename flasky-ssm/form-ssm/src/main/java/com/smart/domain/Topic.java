package com.smart.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Topic extends BaseDomain {
    private static final long serialVersionUID = -8904128877585555604L;
    /* 普通帖子 */
    private static final int NOT_DIGEST_TOPIC = 0;
    /* 精华帖子 */
    private static final int DIGEST_TOPIC = 1;

    private int topicId;
    private String topicTitle;
    private User user;
    private int boardId;
    private MainPost mainPost = new MainPost();
    private Date createTime = new Date();
    private int views;
    private int digest = NOT_DIGEST_TOPIC;
}
