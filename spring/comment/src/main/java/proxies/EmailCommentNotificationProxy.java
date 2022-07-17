package proxies;

import model.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * author : liuanglin
 * date : 2022/7/16 20:27
 * description : 评论代理接口实现类
 * 定义具体的行为
 * 使用邮件发送评论通知
 * 注解 @Component 指示 Spring 实例化该类并将实例作为 bean 添加到 Spring 上下文
 * 使用 @Qualifier 注解为这个实现 bean 命名
 */
@Component
@Qualifier("email")
public class EmailCommentNotificationProxy implements CommentNotificationProxy{
    /*
    实现 sendComment 方法
    使用邮件发送评论通知
     */
    @Override
    public void sendComment(Comment comment) {
        System.out.printf("使用邮件发送评论通知：%s%n", comment);
    }
}
