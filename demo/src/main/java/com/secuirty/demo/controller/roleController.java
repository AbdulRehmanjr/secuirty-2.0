package com.secuirty.demo.controller;

import com.secuirty.demo.model.role;
import com.secuirty.demo.service.roleService;

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

@RestController
@RequestMapping("/role")
public class roleController {
    private Logger log = LoggerFactory.getLogger(userController.class);

    @Autowired
    private roleService rService;


    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody role Role) {
        log.info("Adding role to database {Controller class}");
        return ResponseEntity.ok(rService.addRole(Role));
    }
    
    @GetMapping("/{roleId}")
    public ResponseEntity<?> getUser(@PathVariable("roleId") long roleId) {
        log.info("Getting  role from {Controller class} database with id ="+roleId);
        return ResponseEntity.ok(rService.getRole(roleId));
    }
}
