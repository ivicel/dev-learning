package info.ivicel.tumoblog.admin.controller;

import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(String username, String password, Boolean remember, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password, remember));
        } catch (Exception e) {
            String ip = request.getHeader("X-FORWARDED-FOR") != null ? request.getHeader("X-FORWARDED-FOR")
                    : request.getRemoteAddr();
            logger.info(String.format("用户 [%s@%s] 登录失败: %s", username, ip, e.getMessage()));
            return ResponseResult.of(ResultEnums.LOGIN_ERROR);
        }
        return ResponseResult.of(ResultEnums.LOGIN_SUCCESS);
    }

}
