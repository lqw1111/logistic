package com.logistic.project.service;

import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.exception.LogisticException;

public interface OrderHistoryService {

    OrderHistoryDTO createOrderHistory(OrderHistoryDTO orderHistoryDTO) throws LogisticException;

}
