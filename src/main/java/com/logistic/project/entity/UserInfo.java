package com.logistic.project.entity;

import com.logistic.project.enumeration.Role;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "phone")
    public String phone;

    @Column(name = "email")
    public String email;

    @Column(name = "address")
    public String address;

    @Column(name = "token")
    public String token;

    @Column(name = "active")
    public boolean active;

    @Column(name = "wx_id")
    public String wxId;

    @Column(name = "last_active_time")
    public Timestamp lastActiveTime;

    @Column(name = "create_at", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.INSERT)
    public Timestamp createAt;

    @Column(name = "modified_at", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.ALWAYS)
    public Timestamp modifiedAt;

    @Column(name = "deleted")
    public Boolean deleted;

    @Column(name = "invited_by")
    public Integer invitedBy;

    @Column(name = "invited_code")
    public String invitedCode;

    @Transient
    public String orderCode;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public Timestamp getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Timestamp lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(Integer invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
