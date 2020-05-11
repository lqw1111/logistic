package com.logistic.project.entity;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class UserInfo {

    @Id @GeneratedValue
    private long uid;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        admin,normal
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
