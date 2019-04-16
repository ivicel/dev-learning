package info.ivicel.web.service;

import info.ivicel.web.entity.Post;
import java.util.List;

public interface IPostService {
    Post findById(int id);

    List<Post> findByAuthorId(int authorId);

    List<Post> findAllPost();
}
