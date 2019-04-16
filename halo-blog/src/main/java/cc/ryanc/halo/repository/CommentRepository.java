package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Comment;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.repository.base.BaseRepository;
import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> findByPostAndCommentStatus(Post post, int status);

    List<Comment> findByPostAndCommentStatusNot(Post post, int status);

    List<Comment> findTop5ByOrderByCommentDateDesc();
}
