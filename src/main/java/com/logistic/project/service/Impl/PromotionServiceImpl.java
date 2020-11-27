package com.logistic.project.service.Impl;

import com.logistic.project.controller.BaseController;
import com.logistic.project.dao.repository.PromotionRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.entity.Promotion;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.enumeration.PromotionType;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    public PromotionRepository promotionRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Promotion createPricePromotion(Integer userId, Integer price, Integer monthNum) throws LogisticException {
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(UUID.randomUUID().toString());
        promotion.setUserId(userId);
        promotion.setPromotionTypeId(PromotionType.PRICE.getId());
        promotion.setPrice(price);
        promotion.setValidate(true);
        if (monthNum != null) {
            promotion.setExpireDate(getExpireDate(monthNum));
        }
        Promotion res = promotionRepository.save(promotion);
        return res;
    }

    @Override
    public Promotion createDiscountPromotion(Integer userId, Integer discount, Integer monthNum) throws LogisticException {
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(UUID.randomUUID().toString());
        promotion.setUserId(userId);
        promotion.setPromotionTypeId(PromotionType.DISCOUNT.getId());
        promotion.setDiscount(discount);
        promotion.setValidate(true);
        if (monthNum != null) {
            promotion.setExpireDate(getExpireDate(monthNum));
        }
        Promotion res = promotionRepository.save(promotion);
        return res;
    }

    private Timestamp getExpireDate(Integer monthNum) {
        Date now = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(now);
        rightNow.add(Calendar.MONTH, monthNum);// 日期加3个月
        return new Timestamp(rightNow.getTimeInMillis());
    }

    @Override
    public List<Promotion> findAllPromotionByUser(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
        if (!userInfoRepository.findById(userId).isPresent()) {
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
