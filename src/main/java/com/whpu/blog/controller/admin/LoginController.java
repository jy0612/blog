package com.whpu.blog.controller.admin;

import com.whpu.blog.pojo.User;
import com.whpu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description: 完成登陆操作的控制器
 * @Author jy
 * @Date 2020/4/21
 **/
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    /**
    * @MethodName: login
    * @Description: 后台用户登录
    * @Param: [username, password, session, redirectAttributes]
    * @Return: java.lang.String
    **/
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            redirectAttributes.addFlashAttribute("message","(⁄ ⁄•⁄ω⁄•⁄ ⁄)用户名或密码错误哦，请重新输入");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
