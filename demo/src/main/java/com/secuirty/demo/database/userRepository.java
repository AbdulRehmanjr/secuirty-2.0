package com.secuirty.demo.database;

import com.secuirty.demo.model.user;

import org.springframework.data.jpa.repository.JpaRepository;




public interface userRepository  extends JpaRepository<user, Long> {

    
    public user findByUserName(String username);
}
