package com.logistic.project.controller;

import com.logistic.project.dto.IssueMessageDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    //find by id
    @RequestMapping(value = "/find/{orderId}", method = RequestMethod.GET)
    public UserOrderDTO findById(@PathVariable("orderId") Integer orderId) throws LogisticException {
        return userOrderService.findById(orderId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserOrderDTO update(@RequestBody UserOrderDTO userOrderDTO) throws LogisticException{
        return userOrderService.updateOrder(userOrderDTO);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable("id") Integer OrderId) throws LogisticException{
        userOrderService.deleteOrderById(OrderId);
    }

    @PreAuthorize("hasAnyRole('admin')")
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

    @PreAuthorize("hasAnyRole('admin')")
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

    @RequestMapping(value = "/issue/user/{userId}/userorder/{userOrderId}", method = RequestMethod.PUT)
    public UserOrderDTO issueOrder(@PathVariable("userId") Integer userId, @PathVariable("userOrderId") Integer userOrderId) throws  LogisticException {
        return userOrderService.issueOrder(userId, userOrderId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/update/issue/message", method = RequestMethod.PUT)
    public UserOrderDTO updateIssueMessage(@RequestBody IssueMessageDTO issueMessageDTO) throws LogisticException{
        return userOrderService.updateIssueMessage(issueMessageDTO);
    }

    @RequestMapping(value = "/submit/user/{userId}/userorder/{userOrderId}", method = RequestMethod.PUT)
    public UserOrderDTO submitOrder(@PathVariable("userId") Integer userId,
                                    @PathVariable("userOrderId") Integer userOrderId) throws LogisticException {
        return userOrderService.submitOrder(userId, userOrderId);
    }

    @RequestMapping(value = "/find/user/{userId}", method = RequestMethod.GET)
    public List<UserOrderWithParcelDTO> findUserOrderWithParcel(@PathVariable("userId") Integer userId) throws LogisticException{
        return userOrderService.findUserOrderWithParcel(userId);
    }

    //find all
    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserOrderDTO> findAll() throws LogisticException {
        return userOrderService.findAll();
    }

    //find all status
    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/status/{statusId}", method = RequestMethod.GET)
    public List<UserOrderDTO> findAllByStatus(@PathVariable("statusId") Integer statusId) throws LogisticException {
        return userOrderService.findAllByStatusId(statusId);
    }

    @RequestMapping(value = "/statistics/user/{userId}", method = RequestMethod.GET)
    public Map<String, Integer> statisticsData(@PathVariable("userId") Integer userId) throws LogisticException {
        return userOrderService.statisticsData(userId);
    }
}
