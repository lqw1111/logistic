package com.logistic.project.controller;

import com.logistic.project.dto.PaymentDTO;
import com.logistic.project.entity.Payment;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PaymentDTO> findAll() throws LogisticException {
        return paymentService.findAll();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public List<Map<String, Object>> findAllWithActivity() throws LogisticException {
        return paymentService.findAllWithActivity();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List<PaymentDTO> findPaymentByUserId(@PathVariable("userId") Integer userId) throws LogisticException {
        return paymentService.findPaymentByUserId(userId, getPrincipal());
    }

    @RequestMapping(value = "/user/{userId}/activity", method = RequestMethod.GET)
    public List<Map<String,Object>> findPaymentByUserIdWithActivity(@PathVariable("userId") Integer userId) throws LogisticException {
        return paymentService.findPaymentByUserIdWithActivity(userId, getPrincipal());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public PaymentDTO createPayment(@RequestBody PaymentDTO paymentDTO) throws LogisticException {
        return paymentService.createPayment(paymentDTO);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/paid/{actualPaid}/payment/{paymentId}", method = RequestMethod.POST)
    public PaymentDTO setActualPaid(@PathVariable("actualPaid")BigDecimal actualPaid,
                                    @PathVariable("paymentId") Integer paymentId) throws LogisticException {
        return paymentService.setActualPaid(paymentId, actualPaid);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = "/validate/{paymentId}", method = RequestMethod.POST)
    public PaymentDTO validatePayment(@PathVariable("paymentId") Integer paymentId) throws LogisticException {
        return paymentService.validatePayment(paymentId);
    }
}
