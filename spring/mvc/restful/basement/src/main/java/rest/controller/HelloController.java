package rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : anger
 * date : 2022/7/23 15:50
 * description : RestController
 * 注解 @RestController 标记这个类作为一个 MVC controller
 * RestController 和常规的 Controller 之间最大的区别是
 * RestController 不返回视图，而是直接返回 http response body
 */
@RestController
public class HelloController {

    /*
    @ResponseBody 注解告诉 dispatcher servlet 这个方法不会返回一个视图
    @RestController 中的方法 @ResponseBody 注解可以省略
    直接返回 HTTP response
     */
    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello";
    }
}
