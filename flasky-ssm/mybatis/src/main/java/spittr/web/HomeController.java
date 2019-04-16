package spittr.web;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.data.JdbcSpittleRepository;
import spittr.data.SpittleRepository;

@Controller
public class HomeController {
    private SpittleRepository jdbcSpittleRepository;

    @Autowired
    public HomeController(SpittleRepository jdbcSpittleRepository) {
        this.jdbcSpittleRepository = jdbcSpittleRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("blogs", jdbcSpittleRepository.findBlogs());
        return "home";
    }

    public static class Blog implements Serializable {
        private static final long serialVersionUID = 3137797018853717794L;
        public Integer id;
        public String title;
        public String summary;
        public Date releaseDate;
        public Integer clickHit;
        public Integer replyHit;
        public String content;
        public Integer typeId;
        public String keyWord;
    }

//    @RequestMapping("/login")
//    public String login() {
//        System.out.println("HomeController.login");
//        return "login";
//    }
}
