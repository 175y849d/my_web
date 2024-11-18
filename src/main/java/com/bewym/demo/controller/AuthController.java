package com.bewym.demo.controller;

import com.bewym.demo.service.CodeGenerator;
import com.bewym.demo.service.CodeStorageService;
import com.bewym.demo.service.SmsService;
import com.bewym.demo.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CodeStorageService codeStorageService;

    // 短信验证码请求
    @PostMapping("/form")
    public String sendCode(@RequestParam String phoneNumber) {
        String code = CodeGenerator.generateCode();
        boolean smsSent = SmsService.sendSms(phoneNumber, code);
        if (smsSent) {
            codeStorageService.saveCode(phoneNumber, code);
            return "验证码已发送";
        } else {
            return "短信发送失败，请稍后再试";
        }
    }

    // 验证码登录
    @PostMapping("/login")
    public String login(@RequestParam String phoneNumber, @RequestParam String code) {
        String storedCode = codeStorageService.getCode(phoneNumber);
        if (storedCode != null && storedCode.equals(code)) {
            codeStorageService.deleteCode(phoneNumber); // 删除验证码，防止重复使用
            String token = TokenGenerator.generateToken(phoneNumber); // 生成 JWT
            return "登录成功，Token: " + token;
        } else {
            return "验证码错误或已过期";
        }
    }
}
