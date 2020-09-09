package info.ivicel;

import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private PostRepo postRepo;

    @GetMapping
    public ResponseEntity index() {

        List<Object> posts = postRepo.myFetchAll(null);
        //         findAll((root, query, cb) -> {
        //     query = cb.createQuery();
        //     query.multiselect(root, root.get("author"));
        //     Join<Post, User> join = root.join("author", JoinType.LEFT);
        //
        //
        //     return cb.equal(join.get("id"), root.get("author").get("id"));
        // });

        // List<Post> posts = postRepository.findAll();
        // System.out.println(posts);
        // System.out.println(posts.get(0).getAuthor());
        // return ResponseEntity.ok(posts);
        return ResponseEntity.ok(posts);
    }


}
