package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Comment;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.enums.CommentStatus;
import cc.ryanc.halo.service.base.CrudService;
import java.util.List;

public interface CommentService extends CrudService<Comment, Long> {

    List<Comment> findCommentsByPostAndCommentStatus(Post post, CommentStatus status);

    List<Comment> findCommentsByPostAndCommentStatusNot(Post post, CommentStatus status);

    List<Comment> fidnTop5Comments();
}
