package service;

import lombok.extern.slf4j.Slf4j;
import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import processor.CommentProcessor;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;

/**
 * author : anger
 * date : 2022/7/17 10:12
 * description : 使用 Spring 框架的 CommentService
 * 注解为 @Component 将会被 Spring 实例化并添加到 Spring 上下文中
 * 注解 @Component 是通用的注解
 * 不会有任何所实现的对象的具体职责
 * 注解 @Service 表示这个对象的职责是用于实现用户用例
 * 它和 @Component @Repository 都是 Spring 的原型注解
 */
@Slf4j
@Service
public class FrameworkCommentService {
    private final CommentRepository repository;
    private final CommentNotificationProxy proxy;

    @Autowired
    private ApplicationContext context;

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

    /*
    每次调用 sendComment() 方法
    都需要一个新的 CommentProcessor 对象
    为了达到这个目的
    可以直接在这个 bean 中注入 ApplicationContext
    在 sendComment() 方法中使用 getBean() 方法获取 CommentProcessor 实例

    不能直接使用 @Autowired 注解注入 CommentProcessor
    因为 FrameworkCommentService 是一个单例 bean
    Spring 只会创建该 bean 的一个实例
    同理，Spring 只会将 FrameworkCommentService 的依赖注入一次
    每次调用 sendComment 方法都会使用这个唯一的实例
     */
    public void sendComment(Comment comment) {
        CommentProcessor processor = context.getBean(CommentProcessor.class);
        processor.setComment(comment);
        processor.processComment();
        log.info("sent comment {}", comment);
    }
}
