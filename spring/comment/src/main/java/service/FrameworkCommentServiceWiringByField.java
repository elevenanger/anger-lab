package service;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;

/**
 * author : anger
 * date : 2022/7/17 10:46
 * description : 从实例域进行依赖注入
 */
@Service
public class FrameworkCommentServiceWiringByField {

    /*
    @Autowired 注解于实例域
    实例域不再声明为 final
    Spring 使用默认的构造函数创建该类实例
    并从上下文中将依赖的 bean 注入到对象实例中
     */
    @Autowired
    private CommentRepository repository;
    @Autowired
    private CommentNotificationProxy proxy;

    public void publishComment(Comment comment) {
        repository.storeComment(comment);
        proxy.sendComment(comment);
    }
}
