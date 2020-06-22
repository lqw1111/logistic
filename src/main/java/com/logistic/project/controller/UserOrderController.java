package com.logistic.project.controller;

import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
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
    public UserOrderDTO createOrder(@RequestBody UserOrderDTO userOrderDTO) throws LogisticException {
        return userOrderService.createOrder(userOrderDTO);
    }

    @RequestMapping(value = "/findAll/{userId}", method = RequestMethod.GET)
    public List<UserOrderDTO> findByUserId(@PathVariable("userId") Integer userId) throws LogisticException {
        return userOrderService.findAllByUserId(userId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserOrderDTO update(@RequestBody UserOrderDTO userOrderDTO) throws LogisticException{
        return userOrderService.updateOrder(userOrderDTO);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable("id") Integer OrderId) throws LogisticException{
        userOrderService.deleteOrderById(OrderId);
    }

    @RequestMapping(value = "/approve/user/{userId}/userorder/{userOrderId}", method = RequestMethod.PUT)
    public UserOrderDTO approveOrder(@PathVariable("userId") Integer userId,
                                     @PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return userOrderService.approveOrder(userId, userOrderId);
    }

    @RequestMapping(value = "/close/user/{userId}/userorder/{userOrderId}", method = RequestMethod.PUT)
    public UserOrderDTO closeOrder(@PathVariable("userId") Integer userId,
                                     @PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return userOrderService.closeUserOrder(userId, userOrderId);
    }

    @RequestMapping(value = "/processing/user/{userId}/userorder/{userOrderId}", method = RequestMethod.PUT)
    public UserOrderDTO processingOrder(@PathVariable("userId") Integer userId,
                                     @PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return userOrderService.processingOrder(userId, userOrderId);
    }

    @RequestMapping(value = "/finish/user/{userId}/userorder/{userOrderId}", method = RequestMethod.PUT)
    public UserOrderDTO finishOrder(@PathVariable("userId") Integer userId,
                                     @PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return userOrderService.finishOrder(userId, userOrderId);
    }

    @RequestMapping(value = "/find/{userId}")
    public List<UserOrderWithParcelDTO> findUserOrderWithParcel(@PathVariable("userId") Integer userId) throws LogisticException{
        return userOrderService.findUserOrderWithParcel(userId);
    }
}
