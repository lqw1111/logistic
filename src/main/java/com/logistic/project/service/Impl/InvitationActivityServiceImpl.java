package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.InvitationActivityRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.entity.InvitationActivity;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.InvitationActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InvitationActivityServiceImpl implements InvitationActivityService {

    @Autowired
    private InvitationActivityRepository invitationActivityRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public InvitationActivity createActivity(InvitationActivity invitationActivity) throws LogisticException {
        UserInfo userInfo = userInfoRepository.findById(invitationActivity.getUserId()).orElse(null);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        UserOrder userOrder = userOrderRepository.findById(invitationActivity.getOrderId()).orElse(null);
        if (userOrder == null) {
            throw new LogisticException("Order Doesn't Exist");
        }

        InvitationActivity activity = invitationActivityRepository.findByOrderId(userOrder.getId());
        if(activity != null) {
            throw new LogisticException("Already Have Activity");
        }

        invitationActivity.setStartTime(new Date());
        invitationActivity.setOrderCode("O:" + UUID.randomUUID().toString());
        invitationActivity.setInvitedUserNum(0);
        invitationActivity.setInvitedUserEmail("");

        return invitationActivityRepository.save(invitationActivity);
    }

    @Override
    public List<InvitationActivity> findAll() {
        return invitationActivityRepository.findAll();
    }

    @Override
    public void deleteActivityById(Integer id) {
        invitationActivityRepository.deleteById(id);
    }

    @Override
    public InvitationActivity updateActivity(InvitationActivity invitationActivity) throws LogisticException {
        InvitationActivity dbActive = invitationActivityRepository.findById(invitationActivity.getId()).orElseGet(null);
        if (dbActive == null) {
            throw new LogisticException("Activity Not Exist");
        }
        dbActive.setStartTime(invitationActivity.getStartTime() == null ? dbActive.getStartTime() : invitationActivity.getStartTime());
        dbActive.setTotalDiscountPrice(invitationActivity.getTotalDiscountPrice() == null ? dbActive.getTotalDiscountPrice() : invitationActivity.getTotalDiscountPrice());
        dbActive.setPerUserDiscountPrice(invitationActivity.getPerUserDiscountPrice() == null ? dbActive.getPerUserDiscountPrice() : invitationActivity.getPerUserDiscountPrice());
        dbActive.setInvitedUserNum(invitationActivity.getInvitedUserNum() == null ? dbActive.getInvitedUserNum() : invitationActivity.getInvitedUserNum());
        dbActive.setInvitedUserEmail(invitationActivity.getInvitedUserEmail() == null ? dbActive.getInvitedUserEmail() : invitationActivity.getInvitedUserEmail());
        return invitationActivityRepository.save(dbActive);
    }

    @Override
    public InvitationActivity findByOrderId(Integer orderId) {
        return invitationActivityRepository.findByOrderId(orderId);
    }

    @Override
    public List<InvitationActivity> findByUserId(Integer userId) {
        return invitationActivityRepository.findByUserId(userId);
    }
}
