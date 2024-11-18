package com.bewym.demo.repository;

import com.bewym.demo.entity.User_one;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_one, Long> {

    // 检查用户名是否存在
    boolean existsByName(String name);

    // 通过用户名查找用户
    Optional<User_one> findByName(String name);
}
