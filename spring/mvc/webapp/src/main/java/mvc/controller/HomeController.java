package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author : anger
 * date : 2022/7/21 08:20
 * description : 主页面 controller
 */
@Controller
public class HomeController {

    /*
    Model 存储 controller 发送给视图的数据
     */
    @RequestMapping("/home")
    public String home(Model page,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) String color) {
        /*
        添加需要 controller 发送给 view 的数据
         */
        page.addAttribute("username", name);
        page.addAttribute("color", color);
        return "home";
    }

    /*
    使用 PathVariable 携带变量
     */
    @RequestMapping("/home/{color}")
    public String homeWithColor(@PathVariable String color, Model page) {
        page.addAttribute("username", "anger");
        page.addAttribute("color", color);
        return "home.html";
    }

}
