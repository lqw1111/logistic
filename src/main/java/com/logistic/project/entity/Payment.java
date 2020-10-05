package com.logistic.project.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "user_email")
    public String userEmail;

    @Column(name = "user_name")
    public String userName;

    @Column(name = "order_id")
    public Integer orderId;

    @Column(name = "promotion_code")
    public String promotionCode;

    @Column(name = "price")
    public BigDecimal price;

    @Column(name = "paid")
    public BigDecimal paid;

    @Column(name = "actual_paid")
    public BigDecimal actualPaid;

    @Column(name = "validate")
    public boolean validate;

    @Column(name = "paid_at")
    @Generated(value = GenerationTime.INSERT)
    public Timestamp paidAt;

    public Payment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public BigDecimal getActualPaid() {
        return actualPaid;
    }

    public void setActualPaid(BigDecimal actualPaid) {
        this.actualPaid = actualPaid;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }
}
