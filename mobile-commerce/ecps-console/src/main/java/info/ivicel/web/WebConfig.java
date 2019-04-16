package info.ivicel.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String JSP_PREFIX = "/WEB-INF/templates/";
    private static final String JSP_SUFFIX = ".jsp";

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(getInternalResourceViewResolver());
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        return new InternalResourceViewResolver(JSP_PREFIX, JSP_SUFFIX);
    }
}
