package repositories;

import model.Comment;

/**
 * author : anger
 * date : 2022/7/16 20:21
 * description : 评论持久化数据库接口
 */
public interface CommentRepository {
    /*
    存储评论信息的接口
     */
    void storeComment(Comment comment);
}
