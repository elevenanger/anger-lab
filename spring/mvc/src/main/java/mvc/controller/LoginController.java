package mvc.controller;

import mvc.processor.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author : liuanglin
 * date : 2022/7/22 20:37
 * description : 登录 controller
 */
@Controller
public class LoginController {
    private final LoginProcessor processor;

    public LoginController(LoginProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/login")
    public String loginGet() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        boolean loggedIn;
        processor.setUsername(username);
        processor.setPassword(password);
        loggedIn = processor.login();
        if (loggedIn) {
            model.addAttribute("message", "已登录");
            return "redirect:/main";
        }
        model.addAttribute("message", "登录失败");
        return "login.html";
    }
}
