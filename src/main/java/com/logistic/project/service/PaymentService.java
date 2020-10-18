package com.logistic.project.service;

import com.logistic.project.dto.PaymentDTO;
import com.logistic.project.entity.Payment;
import com.logistic.project.exception.LogisticException;
import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO paymentDTO) throws LogisticException;

    PaymentDTO setActualPaid(Integer paymentId, BigDecimal actualPaid) throws LogisticException;

    PaymentDTO validatePayment(Integer paymentId) throws LogisticException;

    List<PaymentDTO> findPaymentByUserId(Integer userId) throws LogisticException;

    List<PaymentDTO> findAll() throws LogisticException;
}
