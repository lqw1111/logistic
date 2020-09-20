package com.logistic.project.service;

import com.logistic.project.exception.LogisticException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PriceCalculateService {

    public List<Map<String, Object>> calculate(Integer countryId, BigDecimal length, BigDecimal hight, BigDecimal width, BigDecimal weight) throws LogisticException;
}
