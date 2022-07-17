package proxies;

import model.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * author : liuanglin
 * date : 2022/7/17 18:06
 * description : 直接推送评论通知-站内信模式
 * 演示一个接口多个实现的场景下，如何在 spring 上下文中选择正确的实现类的方法
 * 使用 @Primary 注解
 * 当存在多个相同类型 bean
 * 这个 bean 将作为默认的 bean 注入依赖关系
 */
@Component
@Primary
@Qualifier("push")
public class PushCommentNotificationProxy implements CommentNotificationProxy{
    @Override
    public void sendComment(Comment comment) {
        System.out.printf("站内信推送评论通知：%s%n", comment);
    }
}
