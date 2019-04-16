package info.ivicel.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private DataSourceProperties prop;

    @Autowired
    public AppConfig(DataSourceProperties prop) {
        this.prop = prop;
    }

    // @Bean
    // public DataSource dataSource() {
    //     DruidDataSource dataSource = new DruidDataSource();
    //     dataSource.setUsername(prop.getUsername());
    //     dataSource.setPassword(prop.getPassword());
    //     dataSource.setUrl(prop.getUrl());
    //     dataSource.setDriverClassName(prop.getDriverClassName());
    //     dataSource.setInitialSize(2);
    //
    //     return dataSource;
    // }
}
