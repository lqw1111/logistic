package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.PromotionRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.entity.Promotion;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.enumeration.PromotionType;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    public PromotionRepository promotionRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Promotion createPricePromotion(Integer userId, Integer price) throws LogisticException {
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(UUID.randomUUID().toString());
        promotion.setUserId(userId);
        promotion.setPromotionTypeId(PromotionType.PRICE.getId());
        promotion.setPrice(price);
        promotion.setValidate(true);
        Promotion res = promotionRepository.save(promotion);
        return res;
    }

    @Override
    public Promotion createDiscountPromotion(Integer userId, Integer discount) throws LogisticException {
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(UUID.randomUUID().toString());
        promotion.setUserId(userId);
        promotion.setPromotionTypeId(PromotionType.DISCOUNT.getId());
        promotion.setPrice(discount);
        promotion.setValidate(true);
        Promotion res = promotionRepository.save(promotion);
        return res;
    }

    @Override
    public List<Promotion> findAllPromotionByUser(Integer userId) throws LogisticException {
        UserInfo userInfo = userInfoRepository.findById(userId).orElseGet(null);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        return promotionRepository.findAllByUserIdAndValidateIsTrue(userId);
    }

    @Override
    public void invalidatePromotion(String promotionCode, Integer userId) throws LogisticException {
        Promotion promotion = promotionRepository.findByPromotionCode(promotionCode);
        if (promotion == null) {
            throw new LogisticException("Promotion Doesn't Exist");
        }
        if (!promotion.getUserId().equals(userId)) {
            throw new LogisticException("Invalid Promtion Code");
        }
        promotion.setValidate(false);
        promotionRepository.save(promotion);
    }
}
