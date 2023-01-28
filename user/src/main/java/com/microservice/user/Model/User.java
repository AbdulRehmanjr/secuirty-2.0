package com.microservice.user.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.ws.rs.DefaultValue;

@Entity
@Table(name = "USERTABLE")
public class User {

    @Id
    private String userId;
    private String fullName;
    private String userName;
    private String userPassword;
    private String email;
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

    private Set<Role> roles = new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", fullName=" + fullName + ", userName=" + userName + ", userPassword="
                + userPassword + ", email=" + email + ", about=" + about + ", ratings=" + ratings + ", roles=" + roles
                + "]";
    }

}
