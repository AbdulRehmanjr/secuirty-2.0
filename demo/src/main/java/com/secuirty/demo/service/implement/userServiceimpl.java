package com.secuirty.demo.service.implement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.secuirty.demo.database.roleRepository;
import com.secuirty.demo.database.userRepository;
import com.secuirty.demo.model.role;
import com.secuirty.demo.model.user;
import com.secuirty.demo.service.userService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userServiceimpl implements userService{

    private Logger log = LoggerFactory.getLogger(userServiceimpl.class);

    @Autowired
    private userRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private roleRepository rRepo;

    private String Default_Role = "ADMIN";
    
    @Override
    public user addUser(user User) {

        user searchUser = this.userRepo.findByUserName(User.getUserName());

        if(searchUser!=null){
            log.error("User already exists");
            return null;
        }
        else{
            // find the role in database and add in Set of roles 
            role Role = this.rRepo.findByRoleName(Default_Role);
            Set<role> roles = new HashSet<>();
            roles.add(Role);
            User.setRoles(roles);
            User.setUserPassword(encoder.encode(User.getUserPassword()));
            searchUser = this.userRepo.save(User);
            log.info("User created with Role : "+searchUser.getRoles().iterator().next().getRoleName());
        }

        return searchUser;
    }


    @Override
    public user getUser(long id) {
        log.info("Getting  user from  database with id ="+id);
        return this.userRepo.findById(id).get();
    }

    @Override
    public void deleteUser(long id) {
        log.info("Deleting  user from  database with id ="+id);
        user User = this.userRepo.findById(id).get();
        this.userRepo.delete(User);
        
    }

    @Override
    public List<user> getAllUsers() {

        log.info("Getting all users from  database");

        return this.userRepo.findAll();
    }

    
}
