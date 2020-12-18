package com.logistic.project.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "invitation_activity")
public class InvitationActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer orderId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "total_discount_price")
    private BigDecimal totalDiscountPrice;

    @Column(name = "per_user_discount_price")
    private BigDecimal perUserDiscountPrice;

    @Column(name = "invited_user_num")
    private Integer invitedUserNum;

    @Column(name = "invited_user_email")
    private String invitedUserEmail;

    @Column(name = "create_at", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.INSERT)
    private Date createAt;

    @Column(name = "modified", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.ALWAYS)
    private Date modified;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "deleted_at", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.ALWAYS)
    private Date deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(BigDecimal totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public BigDecimal getPerUserDiscountPrice() {
        return perUserDiscountPrice;
    }

    public void setPerUserDiscountPrice(BigDecimal perUserDiscountPrice) {
        this.perUserDiscountPrice = perUserDiscountPrice;
    }

    public Integer getInvitedUserNum() {
        return invitedUserNum;
    }

    public void setInvitedUserNum(Integer invitedUserNum) {
        this.invitedUserNum = invitedUserNum;
    }

    public String getInvitedUserEmail() {
        return invitedUserEmail;
    }

    public void setInvitedUserEmail(String invitedUserEmail) {
        this.invitedUserEmail = invitedUserEmail;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}
