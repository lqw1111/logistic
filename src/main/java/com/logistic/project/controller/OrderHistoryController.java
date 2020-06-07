package com.logistic.project.controller;

import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderhistory")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public OrderHistoryDTO createOrderHistory(@RequestBody OrderHistoryDTO orderHistoryDTO) throws LogisticException{
        return orderHistoryService.createOrderHistory(orderHistoryDTO);
    }

}
