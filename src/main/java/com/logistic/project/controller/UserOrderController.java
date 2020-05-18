package com.logistic.project.controller;

import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserOrder createOrder(@RequestBody UserOrderDTO userOrderDTO) throws LogisticException {
        return userOrderService.createOrder(userOrderDTO);
    }

    @RequestMapping(value = "/findAll/{userId}", method = RequestMethod.GET)
    public List<UserOrder> findByUserId(@PathVariable Integer userId) throws LogisticException {
        return userOrderService.findAllByUserId(userId);
    }

    @RequestMapping(value = "/update/{userId}/{userOrderId}", method = RequestMethod.PUT)
    public UserOrder approveOrder(@PathVariable("userId") Integer userId,
                                  @PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return userOrderService.approve(userId, userOrderId);
    }
}
