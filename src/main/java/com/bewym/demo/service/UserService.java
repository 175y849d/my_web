package com.bewym.demo.service;

import com.bewym.demo.entity.User_one;
import com.bewym.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    public boolean existsByname(String name) {
        return userRepository.existsByname(name);
    }
    public void register(User_one userDto) {
        User_one user = new User_one();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User_one findUserByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password)
                .orElse(null); // 如果找不到返回 null
    }

}
