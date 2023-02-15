package service;

import model.Comment;
import org.junit.jupiter.api.Test;
import proxies.CommentNotificationProxy;
import proxies.EmailCommentNotificationProxy;
import repositories.CommentRepository;
import repositories.DBCommentRepository;

/**
 * author : anger
 * date : 2022/7/16 20:37
 * description : 评论服务测试用例
 */
class CommentServiceTest {

    @Test
    void publishCommentTest() {
        CommentRepository repository = new DBCommentRepository();
        CommentNotificationProxy proxy = new EmailCommentNotificationProxy();

        CommentService commentService = new CommentService(repository, proxy);

        Comment comment = new Comment();
        comment.setAuthor("Daniel");
        comment.setText("Not bad!");

        commentService.publishComment(comment);
    }

}