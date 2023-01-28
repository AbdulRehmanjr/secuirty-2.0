package com.microservice.user.Service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.user.Database.UserRepository;
import com.microservice.user.Model.User;

@Service
public class UserDetailServiceImp implements UserDetailsService{
    
    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImp.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User resultUser = this.userRepo.findByUserName(username);

        if(resultUser == null){
            logger.error("User not found");
            return null;
        }
        
        return new org.springframework.security.core.userdetails.User(resultUser.getUserName(),resultUser.getUserPassword(),resultUser.getRoles());
    }


}

