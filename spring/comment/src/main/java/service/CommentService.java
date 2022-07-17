package service;

import model.Comment;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;

/**
 * author : liuanglin
 * date : 2022/7/16 20:31
 * description : 评论服务类
 * 依赖评论持久化对象 CommentRepository
 * 评论通知对象 CommentNotificationProxy
 */
public class CommentService {

    /*
    声明两个依赖的对象
    作为类的属性
     */
    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    /*
    将依赖的对象作为构造函数的参数
    实例化对象时提供依赖
     */
    public CommentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }

    /*
    实现将 存储评论信息
    和 发送评论通知 的职责委托给依赖对象
     */
    public void publishComment(Comment comment) {
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }

}
