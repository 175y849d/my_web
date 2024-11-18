package com.bewym.demo.controller;

import com.bewym.demo.entity.User_one;
import com.bewym.demo.repository.UserRepository;
import com.bewym.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @GetMapping("/")
    public String ShowInitial_Page() {
        return "initial_page";
    }

    @GetMapping("/form")
    public String showSubmitForm(Model model) {
        model.addAttribute("user", new User_one());
        return "form";
    }

    @PostMapping("/form")
    public String getUserByName(
            @ModelAttribute("user") User_one user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "form"; // 返回表单页面并显示校验错误
        }
        User_one foundUser = userService.findUserByNameAndPassword(user.getName(), user.getPassword());
        if (foundUser == null) {
            model.addAttribute("error", "用户不存在或密码错误！");
            return "form"; // 返回表单页面并显示错误信息
        }
        return "redirect:/home"; // 重定向到主页
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User_one());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User_one user, BindingResult result, Model model) {
        if (userService.existsByname(user.getName())) {
            model.addAttribute("error", "用户名已存在！");
            return "register";
        }
        if (result.hasErrors()) {
            model.addAttribute("error", "注册信息无效！");
            return "register";
        }
        userService.register(user);
        return "redirect:/form";
    }
}




