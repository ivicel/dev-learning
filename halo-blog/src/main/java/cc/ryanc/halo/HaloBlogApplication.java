package cc.ryanc.halo;

import cc.ryanc.halo.config.HaloProperties;
import cc.ryanc.halo.repository.base.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "cc.ryanc.halo.repository",
        repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
@EnableConfigurationProperties(HaloProperties.class)
// todo: @EnableCaching
public class HaloBlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HaloBlogApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HaloBlogApplication.class);
    }
}
