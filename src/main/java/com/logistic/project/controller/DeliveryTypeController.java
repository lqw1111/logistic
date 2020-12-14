package com.logistic.project.controller;

import com.logistic.project.entity.DeliveryType;
import com.logistic.project.service.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deliverytype")
public class DeliveryTypeController {

    @Autowired
    private DeliveryTypeService deliveryTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<DeliveryType> findAll() {
        return deliveryTypeService.findAll();
    }
}
