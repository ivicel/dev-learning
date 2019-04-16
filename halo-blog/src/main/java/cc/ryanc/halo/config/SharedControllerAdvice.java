package cc.ryanc.halo.config;

import cc.ryanc.halo.model.domain.User;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.service.UserService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SharedControllerAdvice {
    private UserService userService;

    public SharedControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("options")
    public Map<String, String> addOptions() {
        return HaloConst.OPTIONS;
    }

    @ModelAttribute("user")
    public User user(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(HaloConst.USER_SESSION_KEY);
        if (user == null) {
            user = userService.fetchById(1L).orElse(new User());
            request.getSession().setAttribute(HaloConst.USER_SESSION_KEY, user);
        }
        return user;
    }

}
