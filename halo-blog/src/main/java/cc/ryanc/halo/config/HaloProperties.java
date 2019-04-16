package cc.ryanc.halo.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Setter
@Getter
@ConfigurationProperties(prefix = "halo")
public class HaloProperties {
    private String imageUploadDirectory;
}
