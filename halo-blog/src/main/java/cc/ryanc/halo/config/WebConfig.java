package cc.ryanc.halo.config;

import cc.ryanc.halo.model.enums.BlogProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer  {
    private HaloProperties haloProperties;

    public WebConfig(HaloProperties haloProperties) {
        this.haloProperties = haloProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        String uploadImageMapLocation = haloProperties.getImageUploadDirectory();
        if (uploadImageMapLocation.startsWith("/")) {
            uploadImageMapLocation = "file://" + uploadImageMapLocation;
        } else {
            uploadImageMapLocation = "classpath:";
        }

        if (!uploadImageMapLocation.endsWith("/")) {
            uploadImageMapLocation += "/";
        }

        registry.addResourceHandler(String.format("/%s/**", BlogProperties.DEFAULT_UPLOAD_IMAGE_URL_PREFIX.getProp()))
                .addResourceLocations(uploadImageMapLocation);
        registry.addResourceHandler("/anatole/source/**")
                .addResourceLocations("classpath:/templates/anatole/source/");
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/message");
        return messageSource;
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // super.configure(http);
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().and()
                    .headers().disable();
                    // .headers().frameOptions().sameOrigin();

        }
    }
}
