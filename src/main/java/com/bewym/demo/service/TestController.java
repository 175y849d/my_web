package com.bewym.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;

@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String testRedis() {
        // 设置一个值到 Redis
        redisTemplate.opsForValue().set("key", "Hello, Redis!");
        return redisTemplate.opsForValue().get("key");
    }
}
