package info.ivicel.hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroHelloWorld {

    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String value = (String)session.getAttribute("key");
        System.out.println("session ----> key = " + value);
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("admin1", "123");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("没有该用户: " + token.getUsername());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("密码不正确: " + token.getPassword());
            } catch (LockedAccountException lae) {
                System.out.println("被锁定: " + token.getPassword());
            } catch (AuthenticationException ae) {
                System.out.println("其他异常");
            }
        }

        if (currentUser.getPrincipal() != null) {
            System.out.println("登录成功: " + currentUser.getPrincipal());
        }
        if (currentUser.hasRole("role1")) {
            System.out.println("找到角色 role1");
        } else {
            System.out.println("没有角色 role1");
        }
        if (currentUser.isPermitted("printer:pring")) {
            System.out.println();
        }
        currentUser.logout();

    }
}
