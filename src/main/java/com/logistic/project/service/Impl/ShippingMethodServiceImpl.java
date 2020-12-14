package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.ShippingMethodRepository;
import com.logistic.project.entity.ShippingMethod;
import com.logistic.project.service.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingMethodServiceImpl implements ShippingMethodService {

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Override
    public List<ShippingMethod> findAll() {
        return shippingMethodRepository.findAll();
    }
}
