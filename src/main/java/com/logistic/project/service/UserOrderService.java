package com.logistic.project.service;

import com.logistic.project.dto.IssueMessageDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
import com.logistic.project.exception.LogisticException;

import java.util.List;
import java.util.Map;

public interface UserOrderService {

    UserOrderDTO createOrder(UserOrderDTO userOrderDTO) throws LogisticException;

    UserOrderDTO updateOrder(UserOrderDTO orderDTO, String username) throws LogisticException;

    UserOrderDTO updateIssueMessage(IssueMessageDTO issueMessageDTO) throws LogisticException;

    List<UserOrderWithParcelDTO> findAllByUserId(Integer userId, String username) throws LogisticException;

    void deleteOrderById(Integer OrderId) throws LogisticException;

    UserOrderDTO approveOrder(Integer userId, Integer userOrderId) throws LogisticException;

    UserOrderDTO closeUserOrder(Integer userId, Integer userOrderId, String username) throws LogisticException;

    UserOrderDTO processingOrder(Integer userId, Integer userOrderId) throws LogisticException;

    UserOrderDTO finishOrder(Integer userId, Integer userOrderId, String username) throws LogisticException;

    UserOrderDTO issueOrder(Integer userId, Integer userOrderId, String username) throws LogisticException;

    //TODO: 返回一个大的object 包括UserOrder和Parcel
    List<UserOrderWithParcelDTO> findUserOrderWithParcel(Integer userId, String username) throws LogisticException;

    UserOrderWithParcelDTO findById(Integer orderId, String username) throws LogisticException;

    UserOrderDTO submitOrder(Integer userId, Integer userOrderId, String username) throws LogisticException;

    List<UserOrderWithParcelDTO> findAll() throws LogisticException;

    List<UserOrderWithParcelDTO> findAllByStatusId(Integer statusId) throws LogisticException;

    Map<String,Integer> statisticsData(Integer userId) throws LogisticException;
}
