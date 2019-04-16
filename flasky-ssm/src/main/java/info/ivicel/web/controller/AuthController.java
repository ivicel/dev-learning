package info.ivicel.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember_me", required = false, defaultValue = "false") boolean rememberMe,
            HttpServletRequest request,
            RedirectAttributes ra) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/";
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            token.setRememberMe(true);
            try {
                subject.login(token);
                return "redirect:/";
            } catch (Exception e) {
                ra.addFlashAttribute("errorMessage", "用户名或密码错误");
                return "redirect:/auth/login";
            }
        }

        return "auth/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        System.out.println("AuthController.register");
        model.addAttribute("action", "");
        return "auth/register";
    }

    @RequestMapping("/reset")
    public String reset() {
        return "reset";
    }

    @RequestMapping("/change-password")
    public String changePassword() {
        return "auth/change_password";
    }

    @RequestMapping("/change-email")
    public String changeEmail() {
        return "auth/change_email";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "auth/logout";
    }
}
