package com.bewym.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CodeStorageService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void saveCode(String phoneNumber, String code) {
        redisTemplate.opsForValue().set("SMS_CODE:" + phoneNumber, code, 5, TimeUnit.MINUTES);
    }

    public String getCode(String phoneNumber) {
        return redisTemplate.opsForValue().get("SMS_CODE:" + phoneNumber);
    }

    public void deleteCode(String phoneNumber) {
        redisTemplate.delete("SMS_CODE:" + phoneNumber);
    }
}
