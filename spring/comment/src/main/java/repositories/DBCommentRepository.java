package repositories;

import model.Comment;
import org.springframework.stereotype.Repository;

/**
 * author : anger
 * date : 2022/7/16 20:23
 * description : Comment 持久化数据库接口实现类
 * 注解 @Repository 声明该对象职责用于管理持久化的数据
 */
@Repository
public class DBCommentRepository implements CommentRepository{

    /*
    不进行实际的数据库存储行为
    先将 comment 对象的内容打印出来
     */
    @Override
    public void storeComment(Comment comment) {
        System.out.printf("存储评论信息：%s%n", comment);
    }
}
