package info.ivicel.shirotest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("MyRealm.doGetAuthorizationInfo 被调用");
        String username = (String)getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> s = new HashSet<>();
        s.add("printer:print");
        s.add("printer:query");
        info.setStringPermissions(s);
        Set<String> r = new HashSet<>();
        r.add("role1");
        info.setRoles(r);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyRealm.doGetAuthenticationInfo 被调用");
        String username = (String)token.getPrincipal();
        System.out.println("username = " + username);
        String password = new String((char [])token.getCredentials());
        if (!"admin".equals(username)) {
            throw new UnknownAccountException("用户名错误");
        }

        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        AuthenticationInfo info = new SimpleAuthenticationInfo();

        return info;
    }
}
