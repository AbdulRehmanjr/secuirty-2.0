package com.microservice.user.Model;


import org.springframework.security.core.GrantedAuthority;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "ROLETABLE")
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    private String roleName;
    

    public long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    @Override
    public String toString() {
        return "role [roleId=" + roleId + ", roleName=" + roleName + "]";
    }

    @Override
    public String getAuthority() {
    
        return  this.getRoleName();
    }

}
