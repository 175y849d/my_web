package com.bewym.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CodeStorageService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long CODE_EXPIRATION_TIME = 5; // 5分钟

    public void saveCode(String phoneNumber, String code) {
        try {
            redisTemplate.opsForValue().set("SMS_CODE:" + phoneNumber, code, CODE_EXPIRATION_TIME, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save code in Redis", e);
        }
    }

    public String getCode(String phoneNumber) {
        try {
            return redisTemplate.opsForValue().get("SMS_CODE:" + phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get code from Redis", e);
        }
    }

    public void deleteCode(String phoneNumber) {
        try {
            redisTemplate.delete("SMS_CODE:" + phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete code from Redis", e);
        }
    }
}

