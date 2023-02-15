package config;

import lombok.extern.slf4j.Slf4j;
import model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SimpleCommentService;

/**
 * author : anger
 * date : 2022/7/19 15:01
 * description : 切面测试
 */
@Slf4j
class AspectConfigurationTest {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AspectConfiguration.class, ProjectConfiguration.class);
    SimpleCommentService service = context.getBean(SimpleCommentService.class);

    @Test
    void testProxyObject() {
        log.info(service.getClass().toString());
    }

    @Test
    void testProxyMethods() {
        log.info("testProxyMethods");
        Comment comment = new Comment();
        comment.setText("proxy");
        comment.setAuthor("anger");
        service.publishComment(comment);
        log.info(service.sendComment(comment));
    }
}