package cc.ryanc.halo.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum PostStatus {
    PUBLISHED(0, "已发布"),
    DRAFT(1, "草稿"),
    RECYCLE(2, "回收站");

    private static final Map<Integer, PostStatus> data;

    static {
        data = new HashMap<>();
        for (PostStatus p : PostStatus.values()) {
            data.put(p.status, p);
        }
    }

    private Integer status;
    private String desc;

    PostStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public boolean check(Integer status) {
        return this.status.equals(status);
    }

    public static PostStatus get(int status) {
        return data.getOrDefault(status, PostStatus.PUBLISHED);
    }
}
