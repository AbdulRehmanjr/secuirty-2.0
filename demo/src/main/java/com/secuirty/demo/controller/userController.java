package com.secuirty.demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secuirty.demo.database.roleRepository;
import com.secuirty.demo.model.role;
import com.secuirty.demo.model.user;
import com.secuirty.demo.service.userService;

@RestController
@RequestMapping("/user")
public class userController {

    private Logger log = LoggerFactory.getLogger(userController.class);

    @Autowired
    private userService uService;

    @GetMapping("/show")
    public ResponseEntity<?> show() {
        return ResponseEntity.ok("SHow");
    }

    @GetMapping("/protected")
    public ResponseEntity<?> pro() {
        return ResponseEntity.ok("protected");
    }
    @GetMapping("/auth")
    public ResponseEntity<?> auth() {
        return ResponseEntity.ok("auth");
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(uService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody user User) {

        user userResponse = uService.addUser(User);

        if (userResponse == null) {
            log.error("User already created");
            return ResponseEntity.badRequest().body("User already exists");
        }
        log.info("Adding user to database {Controller class}");

        return ResponseEntity.ok("user created successfully");
    }

    @GetMapping("/{userId}")
    public user fetchUser(@PathVariable("userId") long userId) {
        log.info("Getting  user from {Controller class} database with id =" + userId);
        return this.uService.getUser(userId);
    }
}
