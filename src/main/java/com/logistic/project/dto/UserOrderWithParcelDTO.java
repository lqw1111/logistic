package com.logistic.project.dto;

import java.math.BigDecimal;
import java.util.List;

public class UserOrderWithParcelDTO{

    private Integer id;

    private Integer userId;

    private Integer statusId;

    private BigDecimal price;

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

    private List<ParcelDTO> parcels;

    public UserOrderWithParcelDTO(UserOrderDTO userOrderDTO, List<ParcelDTO> parcels) {
        this.id = userOrderDTO.getId();
        this.userId = userOrderDTO.getUserId();
        this.statusId = userOrderDTO.getStatusId();
        this.price = userOrderDTO.getPrice();
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
        this.parcels = parcels;
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

    public List<ParcelDTO> getParcels() {
        return parcels;
    }

    public void setParcels(List<ParcelDTO> parcels) {
        this.parcels = parcels;
    }
}