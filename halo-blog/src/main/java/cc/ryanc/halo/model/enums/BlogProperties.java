package cc.ryanc.halo.model.enums;

/**
 * 常用的博客设置 key
 */
public enum BlogProperties {
    // 首页显示文章数量
    INDEX_POSTS("index_posts"),

    INDEX_COMMENTS("index_comments"),

    NEW_COMMENT_NEED_CHECK("new_comment_need_check"),

    DEFAULT_THUMBNAIL("/static/halo-frontend/images/thumbnail/thumbnail.png"),

    DEFAULT_UPLOAD_IMAGE_URL_PREFIX("uploads"),

    BLOG_START("blog_start"),

    THEME("theme"),

    ATTACH_LOC("attach_loc");

    private String prop;

    BlogProperties(String prop) {
        this.prop = prop;
    }

    public String getProp() {
        return this.prop;
    }
}
