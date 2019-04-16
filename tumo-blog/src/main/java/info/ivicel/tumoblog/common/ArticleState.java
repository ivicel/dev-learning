package info.ivicel.tumoblog.common;

import info.ivicel.tumoblog.admin.entity.Article;
import java.util.HashMap;
import java.util.Map;

public enum ArticleState {
    DRAFT(0),
    PUBLISH(1);

    private static final Map<Integer, ArticleState> map;
    static {
        map = new HashMap<>();
        for (ArticleState as : ArticleState.values()) {
            map.put(as.getState(), as);
        }
    }
    private int state;

    ArticleState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    /**
     * 检查文章状态, 如果不在预先设定状态中, 则返回 0, 即存入草稿状态
     * @param state 传入的文章状态, 0: 存入草稿, 1, 发布
     * @return 文章状态, state 为 null 或不存在时, 默认返回 DRAFT
     */
    public static ArticleState getOrDeafult(Integer state) {

        return state == null ? ArticleState.DRAFT : map.getOrDefault(state, ArticleState.DRAFT);
    }
}
