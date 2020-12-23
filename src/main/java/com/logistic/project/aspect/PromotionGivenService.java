package com.logistic.project.aspect;

import com.logistic.project.dao.repository.*;
import com.logistic.project.entity.*;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Aspect
@Component
@Slf4j
public class PromotionGivenService {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private InvitationActivityUserRepository invitationActivityUserRepository;

    @Autowired
    private InvitationActivityRepository invitationActivityRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Pointcut(value = "execution(public com.logistic.project.dto.UserInfoDTO com.logistic.project.controller.UserInfoController.register(com.logistic.project.entity.UserInfo))")
    public void registerPointCut(){};

    @Pointcut(value = "execution(public org.springframework.http.ResponseEntity com.logistic.project.controller.UserInfoController.active(..))")
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
//            promotionService.createDiscountPromotion(userInfo.getUid(), 10, 1);
            promotionService.createDiscountPromotion(userInfo.getInvitedBy(), 10, 1);
        }

        Promotion promotion = promotionService.createDiscountPromotion(userInfo.getUid(), 10, 1);
        log.info("NEW USER REGISTER : " + userInfo.getUsername());
        log.info("NEW USER : " + userInfo.getUsername() + " GET PROMOTION CODE " + promotion.getPromotionCode());
    }

    @AfterReturning(value = "activePointCut()")
    @Transactional(rollbackFor = Exception.class)
    public void activityCheck(JoinPoint joinPoint) throws LogisticException {
        Object[] args = joinPoint.getArgs();
        String email = (String) args[0];
        String userName = (String) args[2];
        UserInfo registerUser = userInfoRepository.findByUsernameAndEmail(userName, email);
        if (registerUser == null) {
            log.warn("PromotionGivenService ==> activityCheck: User Doesn't Exist");
            throw new LogisticException("Decrease Price Fail");
        }
        InvitationActivityUser invitationActivityUser = invitationActivityUserRepository.findByUserId(registerUser.getUid());
        if (invitationActivityUser != null) {
            InvitationActivity invitationActivity = invitationActivityRepository.findByOrderCode(invitationActivityUser.getOrderCode());

            if (invitationActivity == null) {
                log.warn("PromotionGivenService ==> activityCheck: No Such Activity");
                throw new LogisticException("Decrease Price Fail");
            }

            UserOrder invitedUserOrder = userOrderRepository.findByUserIdAndOrderId(invitationActivity.getUserId(), invitationActivity.getOrderId());
            if (invitedUserOrder == null) {
                log.warn("PromotionGivenService ==> activityCheck: Invited User Order Not Exist");
                throw new LogisticException("Decrease Price Fail");
            }

            //payment价格降低
            if (invitationActivity.getPerUserDiscountPrice().multiply(BigDecimal.valueOf(invitationActivity.getInvitedUserNum())).compareTo(invitationActivity.getTotalDiscountPrice()) <= 0) {
                invitedUserOrder.setPrice(invitedUserOrder.getPrice().subtract(BigDecimal.valueOf(invitationActivity.getInvitedUserNum())));
                userOrderRepository.save(invitedUserOrder);
            } else {
                log.warn("Order: " + invitedUserOrder.getId() + " Already Get to The Limit");
            }
            log.info("NEW USER : " + registerUser.getUsername() + " HELP " + invitedUserOrder.getId() + " DECREASE " + invitationActivity.getPerUserDiscountPrice());
        }
    }

}
