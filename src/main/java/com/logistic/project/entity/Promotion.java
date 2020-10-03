package com.logistic.project.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "promotion_code")
    public String promotionCode;

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "discount")
    public Integer discount;

    @Column(name = "price")
    public Integer price;

    @Column(name = "promotion_type_id")
    public Integer promotionTypeId;

    @Column(name = "expire_date")
    public Timestamp expireDate;

    @Column(name = "validate")
    public boolean validate;

    @Column(name = "create_at")
    public Timestamp createAt;

    public Promotion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPromotionTypeId() {
        return promotionTypeId;
    }

    public void setPromotionTypeId(Integer promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
