package com.logistic.project.service;

import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.entity.OrderHistory;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

public interface OrderHistoryService {

    OrderHistoryDTO createOrderHistory(OrderHistoryDTO orderHistoryDTO) throws LogisticException;

    List<Map<String, Object>> findAllSort(String sort, String sortBy) throws LogisticException;

    List<Map<String, Object>> findAllWithOrderInfo() throws LogisticException;

    OrderHistoryDTO updateById(Integer orderHistoryId, OrderHistoryDTO orderHistoryDTO) throws LogisticException;

    void deleteById(Integer orderHistoryId) throws LogisticException;

    List<Map<String, Object>> findAll() throws LogisticException;

    List<Map<String,Object>> findAllWithOrderInfoByUserId(Integer userId) throws LogisticException;
}
