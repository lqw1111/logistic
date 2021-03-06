package com.logistic.project.controller;

import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderhistory")
public class OrderHistoryController extends BaseController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public OrderHistoryDTO createOrderHistory(@RequestBody OrderHistoryDTO orderHistoryDTO) throws LogisticException{
        return orderHistoryService.createOrderHistory(orderHistoryDTO);
    }

    //find all(可以排序，根据分数情况。根据时间)
    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Map<String, Object>> findAll(@RequestParam("sort") String sort, @RequestParam("sortBy") String sortBy) throws LogisticException {
        return orderHistoryService.findAllSort(sort, sortBy);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Map<String, Object>> findAll() throws LogisticException {
        return orderHistoryService.findAll();
    }

    //find all by user id attached with order info
    @RequestMapping(value = "/findAll/info", method = RequestMethod.GET)
    public List<Map<String, Object>> findAllWithOrderInfo() throws LogisticException {
        return orderHistoryService.findAllWithOrderInfo();
    }

    @RequestMapping(value = "/findAll/info/{userId}", method = RequestMethod.GET)
    public List<Map<String, Object>> findAllWithOrderInfoByUserId(@PathVariable("userId") Integer userId) throws LogisticException {
        return orderHistoryService.findAllWithOrderInfoByUserId(userId, getPrincipal());
    }

    //update
    @RequestMapping(value = "/update/{orderHistoryId}", method = RequestMethod.PUT)
    public OrderHistoryDTO updateOrderHistory(@PathVariable("orderHistoryId") Integer orderHistoryId, @RequestBody OrderHistoryDTO orderHistoryDTO) throws LogisticException {
        return orderHistoryService.updateById(orderHistoryId, orderHistoryDTO, getPrincipal());
    }

    //delete
    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/delete/{orderHistoryId}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("orderHistoryId") Integer orderHistoryId) throws LogisticException {
        orderHistoryService.deleteById(orderHistoryId);
    }
}
