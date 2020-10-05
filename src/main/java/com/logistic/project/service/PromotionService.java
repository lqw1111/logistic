package com.logistic.project.service;

import com.logistic.project.entity.Promotion;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface PromotionService {

    Promotion createPricePromotion(Integer userId, Integer price) throws LogisticException;

    Promotion createDiscountPromotion(Integer userId, Integer discount) throws LogisticException;

    List<Promotion> findAllPromotionByUser(Integer userId) throws LogisticException;

    void invalidatePromotion(String promotionCode, Integer userId) throws LogisticException;
}
