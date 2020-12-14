package com.logistic.project.controller;

import com.logistic.project.entity.ShippingMethod;
import com.logistic.project.service.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shippingmethod")
public class ShippingMethodController {

    @Autowired
    private ShippingMethodService shippingMethodService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ShippingMethod> findAll() {
        return shippingMethodService.findAll();
    }
}
