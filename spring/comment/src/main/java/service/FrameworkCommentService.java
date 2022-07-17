package service;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;

/**
 * author : liuanglin
 * date : 2022/7/17 10:12
 * description : 使用 Spring 框架的 CommentService
 * 注解为 @Component 将会被 Spring 实例化并添加到 Spring 上下文中
 * 注解 @Component 是通用的注解
 * 不会有任何所实现的对象的具体职责
 * 注解 @Service 表示这个对象的职责是用于实现用户用例
 * 它和 @Component @Repository 都是 Spring 的原型注解
 */
@Service
public class FrameworkCommentService {
    private final CommentRepository repository;
    private final CommentNotificationProxy proxy;

    /*
    通过在构造函数上使用 @Autowired 注解
    @Autowired 注解自动从 Spring 上下文中获取构造函数中需要的参数类型 bean 实例
    注入到构造函数的参数中
    因为参数类型是接口类型
    Spring 会从上下文中查找实现接口类型的 bean
    @Qualifier("email") 声明需要注入的 bean 的限定名
     */
    @Autowired
    public FrameworkCommentService(
        CommentRepository repository,
        @Qualifier("email") CommentNotificationProxy proxy) {
        this.repository = repository;
        this.proxy = proxy;
    }

    public void publishComment(Comment comment) {
        repository.storeComment(comment);
        proxy.sendComment(comment);
    }

    public CommentRepository getRepository() {
        return repository;
    }
}
