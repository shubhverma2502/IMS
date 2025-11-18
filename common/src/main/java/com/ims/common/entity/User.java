package com.ims.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
public class User {

    @Id
    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String role;
    @Transient
    private String confirmPassword;

    public boolean isPasswordMatchesConfirmPassword() {
        return password.equals(confirmPassword);
    }

    public User() {
        super();
    }

    public User(Long userId, String username, String firstname, String lastname, String password, String role, String confirmPassword) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.confirmPassword = confirmPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
