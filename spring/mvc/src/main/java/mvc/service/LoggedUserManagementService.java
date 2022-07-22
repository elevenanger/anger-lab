package mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * author : liuanglin
 * date : 2022/7/22 21:31
 * description : 登录用户管理服务
 * 创建并维护 session
 * 注解 @SessionScope bean Spring 创建实例并与 HTTP session 相关联
 * 一旦客户端发送请求到服务端
 * 服务端在内存中保留请求 session 信息
 * Spring 在特定客户端的 HTTP session 建立后创建 bean 实例
 * 这个实例可以在 session 有效的时间内被相同的客户端重复利用
 * 存储在 session 中的属性可以在同一个 session 内的所有 HTTP 请求共享
 */
@Service
@SessionScope
public class LoggedUserManagementService {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
