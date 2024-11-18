package com.bewym.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users") // 指定数据库表名称
public class User_one {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成主键
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Column(unique = true, nullable = false) // 唯一且不能为空
    private String name;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    @Column(nullable = false) // 密码不能为空
    private String password;

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
