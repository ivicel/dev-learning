package info.ivicel.tumoblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.entity.Comment;
import info.ivicel.tumoblog.admin.mapper.CommentMapper;
import info.ivicel.tumoblog.admin.service.ICommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findAllPagable(int offset, int num) {
        return commentMapper.findAllDesc(offset, num);
    }

    @Override
    public Long findAllCount() {
        return commentMapper.count();
    }

    @Override
    public Page<Comment> findAll(Comment comment, Integer pageCode, Integer pageSize) {
        return null;
    }

    @Override
    public Long save(Comment article) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }


}
