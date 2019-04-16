package info.ivicel.tumoblog.admin.realm;

import info.ivicel.tumoblog.admin.entity.User;
import info.ivicel.tumoblog.admin.service.IUserService;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {
    private IUserService userService;

    public UserRealm(IUserService userService) {
        this.userService = userService;
    }

    public UserRealm(CacheManager cacheManager, IUserService userService) {
        super(cacheManager);
        this.userService = userService;
    }
    //
    public UserRealm(CredentialsMatcher matcher,
            IUserService userService) {
        super(matcher);
        this.userService = userService;
    }

    public UserRealm(CacheManager cacheManager,
            CredentialsMatcher matcher,
            IUserService userService) {
        super(cacheManager, matcher);
        this.userService = userService;
    }

    /**
     * 权限校验
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


     // 身份校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getUsername());
    }
}
