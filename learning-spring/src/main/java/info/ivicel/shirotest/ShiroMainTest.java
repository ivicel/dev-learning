package info.ivicel.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroMainTest {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (Exception e) {
                System.out.println("登录失败: " + e);
                return;
            }
        }
        System.out.println("登录成功");
        if (currentUser.isPermitted("role1")) {
            System.out.println("role1 角色");
        }
        if (currentUser.isPermitted("printer:print")) {
            System.out.println("printer:print 权限");
        }
    }
}
