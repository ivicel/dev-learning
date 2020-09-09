package info.ivicel;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.sound.sampled.Line.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@SpringBootApplication
public class DemoApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping({"/user", "/me"})
    public ResponseEntity user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return ResponseEntity.ok(map);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**", "/error**")
        //         .permitAll().anyRequest().authenticated().and()
        //         .exceptionHandling()
        //         .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
        //         .and()
        //         .logout()
        //         .logoutSuccessUrl("/")
        //         .permitAll()
        //         .and()
        //         .csrf()
        //         .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        //         .and()
        //         .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        return null;
    }
}
