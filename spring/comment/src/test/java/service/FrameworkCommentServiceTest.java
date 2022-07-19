package service;

import config.ProjectConfiguration;
import config.WiringConfiguration;
import lombok.extern.slf4j.Slf4j;
import model.Comment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/17 10:21
 * description : 测试使用 Spring 框架的服务
 */
@Slf4j
class FrameworkCommentServiceTest {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ProjectConfiguration.class);

    static Comment comment = new Comment();

    @BeforeAll
    static void initComment() {
        comment.setAuthor("anger");
        comment.setText("You are using Spring FrameWork!");
    }

    @Test
    void frameworkCommentServiceTest() {
        FrameworkCommentService service =
            context.getBean(FrameworkCommentService.class);
        assertNotNull(service);

        /*
        无需手动创建 FrameworkCommentService 对象
        也不需要创建 FrameworkCommentService 对象实例化所需要依赖的其它对象
        Spring 会从上下文中自动获取对象实例
        这里只需要使用它即可
         */
        service.publishComment(comment);
    }

    @Test
    void wiringByFieldsTest() {
        FrameworkCommentServiceWiringByField service =
            context.getBean(FrameworkCommentServiceWiringByField.class);
        assertNotNull(service);
        service.publishComment(comment);
    }

    @Test
    void wiringByBeanDefinition() {
        AnnotationConfigApplicationContext context1 =
            new AnnotationConfigApplicationContext(WiringConfiguration.class);
        CommentService service =
            context1.getBean("commentServiceBean", CommentService.class);
        assertNotNull(service);
        service.publishComment(comment);
    }

    @Test
    void sendCommentTest() {
        log.info("sendComment");
        FrameworkCommentService service =
            context.getBean(FrameworkCommentService.class);
        service.sendComment(comment);
    }
}
