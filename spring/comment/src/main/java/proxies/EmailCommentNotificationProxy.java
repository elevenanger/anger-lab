package proxies;

import model.Comment;

/**
 * author : liuanglin
 * date : 2022/7/16 20:27
 * description : 评论代理接口实现类
 * 定义具体的行为
 * 使用邮件发送评论通知
 */
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
