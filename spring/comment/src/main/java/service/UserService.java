package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;

/**
 * author : anger
 * date : 2022/7/17 20:33
 * description : 创建一个新的 Service
 * 演示多个类从 Spring 上下文中注入的相同单例 bean 对应的对象是相同的
 */
@Service
public class UserService {

    @Autowired
    private final CommentRepository repository;

    public UserService(CommentRepository repository) {
        this.repository = repository;
    }

    public CommentRepository getRepository() {
        return repository;
    }
}
