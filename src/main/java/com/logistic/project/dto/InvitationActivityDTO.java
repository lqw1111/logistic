package com.logistic.project.dto;

import java.math.BigDecimal;
import java.util.Date;

public class InvitationActivityDTO {
    private Integer id;
    private Integer orderId;
    private Integer userId;
    private Date startTime;
    private String orderCode;
    private BigDecimal totalDiscountPrice;
    private BigDecimal perUserDiscountPrice;
    private Integer invitedUserNum;
    private String invitedUserEmail;
    private Date createAt;
    private Date modified;
    private boolean deleted;
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
