package info.ivicel.web.dao;

import info.ivicel.web.entity.Post;
import java.util.List;

public interface PostDao {

    Post findById(int id);

    List<Post> findByAuthorId(int authorId);

    List<Post> findAllPost();
}
