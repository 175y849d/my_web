package com.bewym.demo.controller;

import com.bewym.demo.service.CodeGenerator;
import com.bewym.demo.service.CodeStorageService;
import com.bewym.demo.service.SmsService;
import com.bewym.demo.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CodeStorageService codeStorageService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private TokenGenerator tokenGenerator;

    // 请求验证码
    @PostMapping("/form")
    public ResponseEntity<?> sendCode(@RequestParam String phoneNumber) {
        try {
            String code = codeGenerator.generateCode();
            boolean smsSent = Boolean.parseBoolean(smsService.sendSms(phoneNumber, code));
            if (smsSent) {
                codeStorageService.saveCode(phoneNumber, code);
                Map<String, String> response = new HashMap<>();
                response.put("message", "验证码已发送");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(500).body(Map.of("error", "短信发送失败，请稍后再试"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "系统错误: " + e.getMessage()));
        }
    }

    // 验证码登录
    @PostMapping("/form/login")
    public ResponseEntity<?> login(@RequestParam String phoneNumber, @RequestParam String code) {
        try {
            String storedCode = codeStorageService.getCode(phoneNumber);
            if (storedCode == null || !storedCode.equals(code)) {
                return ResponseEntity.status(400).body(Map.of("error", "验证码错误或已过期"));
            }

            codeStorageService.deleteCode(phoneNumber);
            String token = tokenGenerator.generateToken(phoneNumber);

            Map<String, String> response = new HashMap<>();
            response.put("message", "登录成功");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "系统错误: " + e.getMessage()));
        }
    }
}


