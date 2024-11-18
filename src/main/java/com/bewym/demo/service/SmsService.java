package com.bewym.demo.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${sms.api.url}")
    private String smsApiUrl;

    @Value("${sms.api.key}")
    private String apiKey;

    public String sendSms(String phoneNumber, String code) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(smsApiUrl);

            // 构造请求体
            String payload = String.format(
                    "{ \"phoneNumber\": \"%s\", \"code\": \"%s\", \"apiKey\": \"%s\" }",
                    phoneNumber, code, apiKey
            );

            post.setEntity(new StringEntity(payload));
            post.setHeader("Content-Type", "application/json");

            // 发送请求并处理响应
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                if (statusCode == 200) {
                    return "SMS sent successfully!";
                } else {
                    return String.format("Failed to send SMS. Status: %d, Response: %s", statusCode, responseBody);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while sending SMS", e);
        }
    }
}


