package com.bewym.demo.controller;

import com.bewym.demo.entity.User_one;
import com.bewym.demo.repository.UserRepository;
import com.bewym.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 初始页面
    @GetMapping("/")
    public String showInitialPage() {
        return "initial_page";
    }

    // 表单页面
    @GetMapping("/form")
    public String showSubmitForm(Model model) {
        model.addAttribute("user", new User_one());
        return "form";
    }

    @PostMapping("/form")
    public String getUserByName(
            @ModelAttribute("user") @Valid User_one user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "form";
        }

        User_one foundUser = userService.authenticate(user.getName(), user.getPassword());
        if (foundUser == null) {
            model.addAttribute("error", "用户不存在或密码错误！");
            return "form";
        }
        return "redirect:/home";
    }

    // 首页
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    // 注册页面
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User_one());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("user") User_one user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "注册信息无效！");
            return "register";
        }

        if (userService.existsByName(user.getName())) {
            model.addAttribute("error", "用户名已存在！");
            return "register";
        }

        userService.register(user);
        return "redirect:/form";
    }
}





