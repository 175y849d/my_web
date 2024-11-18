package com.bewym.demo.service;
import java.util.Random;

public class CodeGenerator {
    public static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10)); // 随机生成 0-9 的数字
        }
        return code.toString();
    }
}
