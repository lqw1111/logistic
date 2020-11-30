package com.logistic.project.aspect;

import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.Promotion;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class PromotionGivenService {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Pointcut(value = "execution(public com.logistic.project.dto.UserInfoDTO com.logistic.project.controller.UserInfoController.register(com.logistic.project.entity.UserInfo))")
    public void registerPointCut(){};

    @Pointcut(value = "execution(public void com.logistic.project.controller.UserInfoController.active(..))")
    public void activePointCut(){};

    @AfterReturning(value = "activePointCut()")
    @Transactional(rollbackFor = Exception.class)
    public void invitedPromotion(JoinPoint joinPoint) throws LogisticException {
        Object[] args = joinPoint.getArgs();
        String email = (String) args[0];
        String userName = (String) args[2];
        UserInfo userInfo = userInfoRepository.findByUsernameAndEmail(userName, email);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        if (userInfo.getInvitedBy() != null) {
            promotionService.createDiscountPromotion(userInfo.getUid(), 5, 1);
            promotionService.createDiscountPromotion(userInfo.getInvitedBy(), 5, 1);
        }

        Promotion promotion = promotionService.createDiscountPromotion(userInfo.getUid(), 5, 1);
        log.info("NEW USER REGISTER : " + userInfo.getUsername());
        log.info("NEW USER : " + userInfo.getUsername() + " GET PROMOTION CODE " + promotion.getPromotionCode());
    }

}
