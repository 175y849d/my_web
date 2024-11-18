package com.bewym;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysql_test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Users"; // 替换为实际数据库名
        String user = "root";
        String password = "pddexas0"; // 替换为实际密码

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
