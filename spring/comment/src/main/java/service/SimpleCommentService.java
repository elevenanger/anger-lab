package service;

import annotations.ToLog;
import lombok.extern.slf4j.Slf4j;
import model.Comment;
import org.springframework.stereotype.Service;

/**
 * author : liuanglin
 * date : 2022/7/19 16:08
 * description : 不依赖其它类的简单 service
 */
@Slf4j
@Service
public class SimpleCommentService {

    public void publishComment(Comment comment) {
        log.info("发布评论：{}", comment.getText());
    }

    @ToLog
    public String sendComment(Comment comment) {
        log.info("发送评论：{}", comment);
        return "SUCCESS";
    }

    public String readComment(Comment comment) {
        log.info("阅读评论：{}", comment);
        return "FINISHED";
    }
}
