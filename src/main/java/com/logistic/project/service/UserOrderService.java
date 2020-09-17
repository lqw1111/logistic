package com.logistic.project.service;

import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface UserOrderService {

    UserOrderDTO createOrder(UserOrderDTO userOrderDTO) throws LogisticException;

    UserOrderDTO updateOrder(UserOrderDTO orderDTO) throws LogisticException;

    List<UserOrderDTO> findAllByUserId(Integer userId) throws LogisticException;

    void deleteOrderById(Integer OrderId) throws LogisticException;

    UserOrderDTO approveOrder(Integer userId, Integer userOrderId) throws LogisticException;

    UserOrderDTO closeUserOrder(Integer userId, Integer userOrderId) throws LogisticException;

    UserOrderDTO processingOrder(Integer userId, Integer userOrderId) throws LogisticException;

    UserOrderDTO finishOrder(Integer userId, Integer userOrderId) throws LogisticException;

    UserOrderDTO issueOrder(Integer userId, Integer userOrderId) throws LogisticException;

    //TODO: 返回一个大的object 包括UserOrder和Parcel
    List<UserOrderWithParcelDTO> findUserOrderWithParcel(Integer userId) throws LogisticException;

    UserOrderDTO findById(Integer orderId) throws LogisticException;

    UserOrderDTO submitOrder(Integer userId, Integer userOrderId) throws LogisticException;

    List<UserOrderDTO> findAll() throws LogisticException;

    List<UserOrderDTO> findAllByStatusId(Integer statusId) throws LogisticException;
}
