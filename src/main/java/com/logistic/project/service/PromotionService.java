package com.logistic.project.service;

import com.logistic.project.entity.Promotion;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface PromotionService {

    Promotion createPricePromotion(Integer userId, Integer price, Integer monthNum) throws LogisticException;

    Promotion createDiscountPromotion(Integer userId, Integer discount, Integer monthNum) throws LogisticException;

    List<Promotion> findAllPromotionByUser(Integer userId, String username) throws LogisticException;

    void invalidatePromotion(String promotionCode, Integer userId) throws LogisticException;
}
