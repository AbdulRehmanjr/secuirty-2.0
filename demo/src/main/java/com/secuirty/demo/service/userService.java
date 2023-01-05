package com.secuirty.demo.service;

import java.util.List;
import java.util.Set;

import com.secuirty.demo.model.user;




public interface userService {
    
    public user addUser(user User);

    public user getUser(long id);
    
    public void deleteUser(long id);


    public List<user> getAllUsers();
}
