package com.bewym.demo.service;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeGenerator {
    public static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
