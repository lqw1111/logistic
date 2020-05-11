package com.logistic.project.service;

import com.logistic.project.entity.UserInfo;

public interface UserInfoService {
    public UserInfo findByUsername(String username);
}
