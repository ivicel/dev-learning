package cc.ryanc.halo.utils;

import cc.ryanc.halo.model.domain.Comment;
import cn.hutool.core.collection.CollectionUtil;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommentUtil {

    public static List<Comment> makeUpCommentTree(List<Comment> comments) {
        if (CollectionUtil.isEmpty(comments)) {
            return Collections.emptyList();
        }

        comments.sort(Comparator.reverseOrder());
        Deque<Comment> originalComments = new ArrayDeque<>(comments);
        List<Comment> result = new LinkedList<>();
        Map<Long, Comment> map = new HashMap<>();

        while (!originalComments.isEmpty()) {
            Comment comment = originalComments.poll();
            if (comment.getCommentParent() == 0) {
                map.put(comment.getCommentId(), comment);
                result.add(comment);
            } else if (map.containsKey(comment.getCommentParent())) {
                map.get(comment.getCommentParent()).addChildComment(comment);
                map.put(comment.getCommentId(), comment);
            } else {
                originalComments.offer(comment);
            }
        }
        return result;
    }
}
