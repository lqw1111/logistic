package com.logistic.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonIgnoreProperties(value = {"deleted", "originPrice"})
public class UserOrderDTO {

    private Integer id;

    private Integer userId;

    private Integer statusId;

    private BigDecimal price;

    private BigDecimal originPrice;

    private String description;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private String receiverPostCode;

    private String orderId;

    private String trackNumber;

    private String senderName;

    private String senderAddress;

    private String senderPhone;

    private String senderPostCode;

    private String orderComment;

    private String pathInfo;

    private String weight;

    private String volumn;

    private String expectDeliveryDate;

    private Integer shippingMethod;

    private Integer deliveryType;

    private Timestamp createAt;

    private Timestamp modifiedAt;

    private String paymentInfo;

    private String issueMessage;

    private boolean deleted;

    public UserOrderDTO(){}

    public UserOrderDTO(UserOrderDTO userOrderDTO) {
        this.id = userOrderDTO.getId();
        this.userId = userOrderDTO.getUserId();
        this.statusId = userOrderDTO.getStatusId();
        this.price = userOrderDTO.getPrice();
        this.originPrice = userOrderDTO.getOriginPrice();
        this.description = userOrderDTO.getDescription();
        this.receiverName = userOrderDTO.getReceiverName();
        this.receiverPhone = userOrderDTO.getReceiverPhone();
        this.receiverAddress = userOrderDTO.getReceiverAddress();
        this.receiverPostCode = userOrderDTO.getReceiverPostCode();
        this.orderId = userOrderDTO.getOrderId();
        this.trackNumber = userOrderDTO.getTrackNumber();
        this.senderName = userOrderDTO.getSenderName();
        this.senderAddress = userOrderDTO.getSenderAddress();
        this.senderPhone = userOrderDTO.getSenderPhone();
        this.senderPostCode = userOrderDTO.getSenderPostCode();
        this.orderComment = userOrderDTO.getOrderComment();
        this.pathInfo = userOrderDTO.getPathInfo();
        this.weight = userOrderDTO.getWeight();
        this.volumn = userOrderDTO.getVolumn();
        this.expectDeliveryDate = userOrderDTO.getExpectDeliveryDate();
        this.shippingMethod = userOrderDTO.getShippingMethod();
        this.deliveryType = userOrderDTO.getDeliveryType();
        this.createAt = userOrderDTO.getCreateAt();
        this.modifiedAt = userOrderDTO.getModifiedAt();
        this.paymentInfo = userOrderDTO.getPaymentInfo();
        this.issueMessage = userOrderDTO.getIssueMessage();
        this.deleted = userOrderDTO.isDeleted();
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
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

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolumn() {
        return volumn;
    }

    public void setVolumn(String volumn) {
        this.volumn = volumn;
    }

    public String getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    public void setExpectDeliveryDate(String expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }

    public Integer getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
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

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }
}
