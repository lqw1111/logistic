package com.logistic.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistic.project.enumeration.ParcelStatus;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="parcel")
@JsonIgnoreProperties(value = {"createAt", "modifiedAt", "deleted"})
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_address")
    private String senderAddress;

    @Column(name = "sender_phone")
    private String senderPhone;

    @Column(name = "sender_postcode")
    private String senderPostCode;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "receiver_postcode")
    private String receiverPostCode;

    //TODO: 讨论content的类型
    @Column(name = "content_type")
    private String contentType;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "parcel_status")
    private ParcelStatus parcelStatus;

    @Column(name = "user_order_id")
    private int userOrderId;

    @Column(name = "create_at", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.INSERT)
    private Timestamp createAt;

    @Column(name = "modified_at", insertable = false, updatable = false, nullable = false)
    @Generated(value = GenerationTime.ALWAYS)
    private Timestamp modifiedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "comment")
    public String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderPostCode() {
        return senderPostCode;
    }

    public void setSenderPostCode(String senderPostCode) {
        this.senderPostCode = senderPostCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public int getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Integer userOrderId) {
        this.userOrderId = userOrderId;
    }

    public void setUserOrderId(int userOrderId) {
        this.userOrderId = userOrderId;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
