package info.ivicel.tumoblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {
        "info.ivicel.tumoblog.admin.config",
        "info.ivicel.tumoblog.admin.controller",
        "info.ivicel.tumoblog.admin.service.impl",
        "info.ivicel.tumoblog.site.controller",
        "info.ivicel.tumoblog.admin.controlleradvice"
})
@MapperScan("info.ivicel.tumoblog.admin.mapper")
public class TumoBlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TumoBlogApplication.class, args);
    }

}
