package com.logistic.project.controller;

import com.logistic.project.entity.PaymentType;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PaymentType> findAll() throws LogisticException {
        return paymentTypeService.findAllPaymentType();
    }
}