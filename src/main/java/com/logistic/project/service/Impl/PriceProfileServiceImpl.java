package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.PriceProfileRepository;
import com.logistic.project.entity.PriceProfile;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PriceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceProfileServiceImpl implements PriceProfileService {

    @Autowired
    private PriceProfileRepository priceProfileRepository;

    @Override
    public List<PriceProfile> findAll() throws LogisticException {
        return priceProfileRepository.findAll();
    }
}
