package com.secuirty.demo.database;

import com.secuirty.demo.model.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepository extends JpaRepository<role,Long>{
    
    public role findByRoleName(String roleName);
}
