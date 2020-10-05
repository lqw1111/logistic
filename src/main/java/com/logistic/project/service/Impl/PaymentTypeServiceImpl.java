package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.PaymentTypeRepository;
import com.logistic.project.entity.PaymentType;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public List<PaymentType> findAllPaymentType() throws LogisticException {
        return paymentTypeRepository.findAll();
    }
}
