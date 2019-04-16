package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Comment;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.enums.CommentStatus;
import cc.ryanc.halo.repository.CommentRepository;
import cc.ryanc.halo.service.CommentService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl extends AbstractCrudService<Comment, Long> implements CommentService {
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
        this.commentRepository = repository;
    }


    @Override
    public List<Comment> findCommentsByPostAndCommentStatus(Post post, CommentStatus status) {
        return commentRepository.findByPostAndCommentStatus(post, status.getStatus());
    }

    @Override
    public List<Comment> findCommentsByPostAndCommentStatusNot(Post post, CommentStatus status) {
        return commentRepository.findByPostAndCommentStatusNot(post, status.getStatus());
    }

    @Override
    public List<Comment> fidnTop5Comments() {
        return commentRepository.findTop5ByOrderByCommentDateDesc();
    }
}
