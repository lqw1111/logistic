package com.logistic.project.controller;

import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserInfo register(@RequestBody UserInfoDTO userInfo) throws LogisticException {
        return userInfoService.insertUser(userInfo);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public UserInfo updateUserByName(@RequestBody UserInfoDTO userInfoDTO) throws LogisticException {
        return userInfoService.updateUserByName(userInfoDTO);
    }

    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    public List<UserInfoDTO> findAllUser() throws LogisticException{
        return userInfoService.findAllUser();
    }

}
