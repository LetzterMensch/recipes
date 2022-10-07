package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByEmail(String email);
}
