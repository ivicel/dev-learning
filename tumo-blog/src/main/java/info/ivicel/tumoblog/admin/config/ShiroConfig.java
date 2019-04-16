package info.ivicel.tumoblog.admin.config;

import info.ivicel.tumoblog.admin.crypt.BCryptCredentials;
import info.ivicel.tumoblog.admin.realm.UserRealm;
import info.ivicel.tumoblog.admin.service.IUserService;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public Realm realm(IUserService userService) {
        UserRealm realm = new UserRealm(userService);
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new BCryptCredentials();
    }

    // todo: ehcache 管理
    // @Bean
    // public CacheManager cacheManager() {
    //     EhCacheManager cacheManager = new EhCacheManager();
    //     return cacheManager;
    // }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        filterFactory.setLoginUrl("/admin/login");
        filterFactory.setSecurityManager(securityManager);

        Map<String, String> map = new LinkedHashMap<>();
        // map.put("/admin/login", "anon");
        // map.put("/admin/logout", "logout");
        // map.put("/admin/**", "authc");
        map.put("/**", "anon");
        filterFactory.setFilterChainDefinitionMap(map);

        return filterFactory;
    }
}
