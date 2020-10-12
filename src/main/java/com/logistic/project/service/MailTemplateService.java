package com.logistic.project.service;

import com.logistic.project.entity.Payment;
import com.logistic.project.entity.UserInfo;

public interface MailTemplateService {

    String contructActiveEmail(UserInfo userInfo, String activeUrl);

    String constructContent(UserInfo userInfo, String newPassword);

    String paymentSuccessEmail(UserInfo userInfo, Payment payment);
}
