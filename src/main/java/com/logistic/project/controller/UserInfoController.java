package com.logistic.project.controller;

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
    public UserInfo register(@RequestBody UserInfo userInfo) throws LogisticException {
        return userInfoService.insertUser(userInfo);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public UserInfo updateUserByName(@RequestBody UserInfo userInfo) throws LogisticException {
        return userInfoService.updateUserByName(userInfo);
    }

    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    public List<UserInfo> findAllUser() throws LogisticException{
        return userInfoService.findAllUser();
    }

}
