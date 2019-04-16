package info.ivicel.web.service.impl;

import info.ivicel.web.dao.PostDao;
import info.ivicel.web.entity.Post;
import info.ivicel.web.service.IPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostServiceImpl implements IPostService {
    private PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public Post findById(int id) {
        return postDao.findById(id);
    }

    public List<Post> findByAuthorId(int authorId) {
        return postDao.findByAuthorId(authorId);
    }

    @Override
    public List<Post> findAllPost() {
        return postDao.findAllPost();
    }
}
