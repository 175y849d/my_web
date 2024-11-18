package com.bewym.demo.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SmsService {
    public static boolean sendSms(String phoneNumber, String code) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost("https://dysmsapi.aliyuncs.com/");

            // 构造请求内容（根据服务商 API 文档）
            String payload = "{ \"phoneNumber\": \"" + phoneNumber + "\", \"code\": \"" + code + "\" }";
            post.setEntity(new StringEntity(payload));
            post.setHeader("Content-Type", "application/json");

            CloseableHttpResponse response = httpClient.execute(post);
            return response.getStatusLine().getStatusCode() == 200; // 返回状态
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
