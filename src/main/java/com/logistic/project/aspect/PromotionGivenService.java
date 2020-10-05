package com.logistic.project.aspect;

import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.Promotion;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PromotionGivenService {

    @Autowired
    private PromotionService promotionService;

    @Pointcut(value = "execution(public com.logistic.project.dto.UserInfoDTO com.logistic.project.controller.UserInfoController.register(com.logistic.project.entity.UserInfo))")
    public void pointCut(){};

    @AfterReturning(value = "pointCut()",returning = "result")
    public void newUserPromotion(JoinPoint joinPoint, UserInfoDTO result) throws LogisticException {
        Promotion promotion = promotionService.createDiscountPromotion(result.getUid(), 5);
        log.info("新用户注册 : " + result.getUsername());
        log.info("新用户 : " + result.getUsername() + "获得优惠券 " + promotion.getPromotionCode());
    }

}
