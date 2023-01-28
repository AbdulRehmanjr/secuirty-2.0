package com.secuirty.demo.controller;

import java.security.Principal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secuirty.demo.config.jwt.jwtUtils;
import com.secuirty.demo.model.JwtRequest;
import com.secuirty.demo.model.JwtResponse;
import com.secuirty.demo.service.implement.userDetailServiceimpl;

@RestController
@RequestMapping("/token")
public class authenticateController {
    
    private Logger log = LoggerFactory.getLogger(authenticateController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private userDetailServiceimpl userDetailsService;
    @Autowired
    private jwtUtils jwtUtil;

    // generate token 
    @PostMapping("/generate-token")
    public ResponseEntity<?>generateToken(@RequestBody JwtRequest request) throws UsernameNotFoundException{

        if(request == null){
            log.error("request is null");
            return ResponseEntity.badRequest().body("request is null");
        }
        else{
            log.info("request username: "+request.getUserName());
        }
        
        log.info("/POST  generate-token");
            try {
                    authenticate(request.getUserName(), request.getUserPassword());
                // log.info("Authentication {Token class } "+request.getUserName());
            } catch (Exception e) {
                    log.error("User not found exception");
                    return ResponseEntity.badRequest().body(e.getMessage());
            }
            // authentication successful s
         UserDetails user =  this.userDetailsService.loadUserByUsername(request.getUserName());
          log.info("user: "+user.getUsername()+" "+user.getPassword());
          String token =   this.jwtUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }




    private void authenticate(String username, String password) throws Exception {
            
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // log.info("Authentication {Authentication manager} "+username);
        } catch (DisabledException e) {
            // log.error("USER DISABLED  {Authenticate Controller} "+e.getMessage() );
            throw new Exception("USER DISABLED");
        }
        catch(BadCredentialsException e){
            // log.error("INVALID CREDENTIALS {Authenticate Controller} "+e.getMessage() );
            throw new Exception("INVALID CREDENTIALS");
        }

    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){

        return (User)this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
