package com.logistic.project.service;

import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface UserInfoService {
    UserInfo findByUsername(String username);

    UserInfo insertUser(UserInfo userInfo) throws LogisticException;

    UserInfo updateUserByName(UserInfo userInfo) throws LogisticException ;

    List<UserInfo> findAllUser() throws LogisticException;
}
