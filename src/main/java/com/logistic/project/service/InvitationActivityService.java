package com.logistic.project.service;

import com.logistic.project.entity.InvitationActivity;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface InvitationActivityService {
    InvitationActivity createActivity(InvitationActivity invitationActivity) throws LogisticException;

    List<InvitationActivity> findAll();

    void deleteActivityById(Integer id);

    InvitationActivity updateActivity(InvitationActivity invitationActivity) throws LogisticException;

    InvitationActivity findByOrderId(Integer orderId);

    List<InvitationActivity> findByUserId(Integer userId);
}
