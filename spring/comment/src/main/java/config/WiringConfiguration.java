package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proxies.CommentNotificationProxy;
import proxies.EmailCommentNotificationProxy;
import repositories.CommentRepository;
import repositories.DBCommentRepository;
import service.CommentService;

/**
 * author : anger
 * date : 2022/7/17 13:59
 * description : 演示通过配置文件进行 bean 注入
 */
@Configuration
public class WiringConfiguration {

    /*
    创建依赖的 bean
     */
    @Bean("commentRepoBean")
    public CommentRepository commentRepository() {
        return new DBCommentRepository();
    }

    @Bean("commentProxyBean")
    public CommentNotificationProxy commentNotificationProxy() {
        return new EmailCommentNotificationProxy();
    }

    /*
    通过 bean 方法参数注入依赖类型的 bean
    @Bean 注解的方法所生成的 bean 对应的对象类型无需原型注解
    任何类型对象都可以
     */
    @Bean("commentServiceBean")
    public CommentService commentService(
        @Qualifier("commentRepoBean") CommentRepository repository,
        @Qualifier("commentProxyBean") CommentNotificationProxy proxy) {
        return new CommentService(repository, proxy);
    }
}
