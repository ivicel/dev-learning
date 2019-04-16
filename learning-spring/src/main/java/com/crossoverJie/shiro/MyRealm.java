package com.crossoverJie.shiro;

import com.crossoverJie.pojo.T_user;
import com.crossoverJie.service.T_userService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {
    @Resource
    private T_userService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("---------------> MyRealm.doGetAuthorizationInfo");
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleName = userService.findRoles(username);
        Set<String> permissions = userService.findPermissions(username);
        info.setRoles(roleName);
        info.setStringPermissions(permissions);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        T_user user = userService.findUserByUsername(username);
        System.out.println("-------------------------> MyRealm.doGetAuthenticationInfo: " + user);
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "a");
        }

        return null;
    }
}
