package mvc.processor;

import mvc.service.LoggedUserManagementService;
import mvc.service.LoggingCountService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * author : liuanglin
 * date : 2022/7/22 20:49
 * description : 登录处理程序
 * 注解 @RequestScope 是 Spring web 的注解
 * 它是 @Scope 注解的特化
 * 声明它的声明周期与当前的 web 请求绑定
 * 每次请求都会创建一个新的实例
 */
@Component
@RequestScope
public class LoginProcessor {
    private String username;
    private String password;

    private final LoggedUserManagementService service;
    private final LoggingCountService loggingCountService;

    public LoginProcessor(LoggedUserManagementService service,
                          LoggingCountService loggingCountService) {
        this.service = service;
        this.loggingCountService = loggingCountService;
    }

    public boolean login() {
        loggingCountService.increment();
        boolean loginResult = "anger".equals(this.getUsername()) &&
            "anger".equals(this.getPassword());
        if (loginResult)
            service.setUsername(username);
        return loginResult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
