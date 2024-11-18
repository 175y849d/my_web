package com.bewym.demo.service;

import com.bewym.demo.entity.User_one;
import com.bewym.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public void register(User_one userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userDto);
    }

    public User_one authenticate(String name, String password) {
        return userRepository.findByName(name)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }
}

