package com.logistic.project.service;

import com.logistic.project.dto.JsonResponse;
import com.logistic.project.dto.ResetPasswordDTO;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserInfoService {
    UserInfo findByUsername(String username);

    UserInfoDTO insertUser(UserInfo userInfo) throws LogisticException;

    UserInfoDTO updateUserByName(UserInfoDTO userInfoDTO) throws LogisticException ;

    List<UserInfoDTO> findAllUser() throws LogisticException;

    UserInfoDTO getUserInfo(String username) throws LogisticException;

    List<UserInfoDTO> findAllByLastActive() throws LogisticException;

    void forgetPassword(String userEmail) throws LogisticException;

    JsonResponse resetPassword(ResetPasswordDTO resetPasswordDTO) throws LogisticException;

    ResponseEntity<Object> activeAccount(String userEmail, String token, String userName) throws LogisticException;

    void isActive(String username) throws LogisticException;
}
