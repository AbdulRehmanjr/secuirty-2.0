package com.microservice.user.Database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.user.Model.User;

public interface UserRepository extends JpaRepository<User,String>{

    User findByUserName(String username);
    
}
