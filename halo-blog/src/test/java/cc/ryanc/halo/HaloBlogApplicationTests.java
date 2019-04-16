package cc.ryanc.halo;


import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.repository.PostRepository;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.service.CommentService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"application.yml"},
        classes = {HaloBlogApplication.class},
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class HaloBlogApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Test
    public void contextLoads() {
        List<Post> posts = postRepository.findTop5ByOrderByPostDateDesc();
        posts.forEach(c -> {
            System.out.println("-----------------------------");
            System.out.println(c.getPostId() + ", " + c.getPostTitle());

            System.out.println("-----------------------------");
        });
    }

}
