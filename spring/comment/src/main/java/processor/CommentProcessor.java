package processor;

import lombok.extern.slf4j.Slf4j;
import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repositories.CommentRepository;

/**
 * author : anger
 * date : 2022/7/19 09:56
 * description : 处理评论
 * 需要对评论对象进行修改操作
 */
@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentProcessor {

    @Autowired
    private CommentRepository repository;
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /*
    对评论对象进行修改操作
     */
    public void processComment() {
        String text = comment.getText();
        comment.setText(text + " processed comment");
        repository.storeComment(comment);
    }
}
