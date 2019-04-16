package cc.ryanc.halo.model.enums;


// todo: 更明确的 post type 信息
public enum PostType {
    POST_TYPE_POST(0, "post"),
    POST_TYPE_PAGE(1, "page");

    private Integer code;
    private String type;

    PostType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
