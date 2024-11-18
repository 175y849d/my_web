package com.bewym.demo.repository;
import com.bewym.demo.entity.User_one;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_one, Long>{
    boolean existsByname(String name);
     Optional<User_one> findByNameAndPassword(String name,String password);
     boolean existsByName(String name);
}

