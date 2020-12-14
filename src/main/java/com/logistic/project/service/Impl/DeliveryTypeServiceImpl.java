package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.DeliveryTypeRepository;
import com.logistic.project.entity.DeliveryType;
import com.logistic.project.service.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    @Override
    public List<DeliveryType> findAll() {
        return deliveryTypeRepository.findAll();
    }
}
