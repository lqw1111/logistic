package com.logistic.project.service;

import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface UserInfoService {
    UserInfo findByUsername(String username);

    UserInfoDTO insertUser(UserInfoDTO userInfoDTO) throws LogisticException;

    UserInfoDTO updateUserByName(UserInfoDTO userInfoDTO) throws LogisticException ;

    List<UserInfoDTO> findAllUser() throws LogisticException;
}
