package proxies;

import model.Comment;

/**
 * author : anger
 * date : 2022/7/16 20:26
 * description : 评论代理顶层抽象类
 */
public interface CommentNotificationProxy {
    /*
    发送评论的行为
     */
    void sendComment(Comment comment);
}
