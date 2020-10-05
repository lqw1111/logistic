package com.logistic.project.service;

import com.logistic.project.entity.PaymentType;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface PaymentTypeService {

    List<PaymentType> findAllPaymentType() throws LogisticException;
}
