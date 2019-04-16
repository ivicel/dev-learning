package cc.ryanc.halo.model.enums;

public enum CommentStatus {
    PUBLISHED(0, "已发布"),
    CHECKING(1, "待审核"),
    RECYCLE(2, "回收站");

    private int status;
    private String desc;

    CommentStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
