package persist.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * author : liuanglin
 * date : 2022/7/25 15:26
 * description : 项目配置类
 */
@Configuration
public class ProjectConfiguration {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /*
    自定义数据源
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("persistence-1");
        config.setJdbcUrl(datasourceUrl);
        config.setDriverClassName(driverClass);
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(1000);
        config.setTransactionIsolation(
            String.valueOf(Connection.TRANSACTION_READ_COMMITTED));
        config.setMaximumPoolSize(10);
        return new HikariDataSource(config);
    }
}
