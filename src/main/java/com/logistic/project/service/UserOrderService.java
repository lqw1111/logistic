package com.logistic.project.service;

import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface UserOrderService {

    UserOrder createOrder(UserOrderDTO userOrderDTO) throws LogisticException;

    UserOrder updateOrder(UserOrder order) throws LogisticException;

    List<UserOrder> findAllByUserId(Integer id) throws LogisticException;

    void deleteOrderById(Integer id) throws LogisticException;

    UserOrder approve(Integer userId, Integer userOrderId) throws LogisticException;
}
