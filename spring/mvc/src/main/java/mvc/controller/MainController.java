package mvc.controller;

import mvc.service.LoggedUserManagementService;
import mvc.service.LoggingCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author : liuanglin
 * date : 2022/7/22 21:43
 * description : 登陆后的主页 Controller
 */
@Controller
public class MainController {

    private final LoggedUserManagementService service;
    private final LoggingCountService loggingCountService;

    public MainController(LoggedUserManagementService service,
                          LoggingCountService countService) {
        this.service = service;
        this.loggingCountService = countService;
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String logout,
                       Model model) {
        if (logout != null)
            service.setUsername(null);
        String username = service.getUsername();
        if (username == null)
            return "redirect:/login";
        int count = loggingCountService.getCount();
        model.addAttribute("username", username);
        model.addAttribute("loginCount", count);
        return "main.html";
    }
}
