package com.logistic.project.controller;

import com.logistic.project.dto.JsonResponse;
import com.logistic.project.dto.ResetPasswordDTO;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserInfoDTO register(@RequestBody UserInfo userInfo) throws LogisticException {
        return userInfoService.insertUser(userInfo);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public UserInfoDTO updateUserByName(@RequestBody UserInfoDTO userInfoDTO) throws LogisticException {
        return userInfoService.updateUserByName(userInfoDTO);
    }

    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    public List<UserInfoDTO> findAllUser() throws LogisticException{
        return userInfoService.findAllUser();
    }

    @RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
    public UserInfoDTO getUserInfo(@PathVariable("username") String username) throws LogisticException{
        return userInfoService.getUserInfo(username);
    }

    @RequestMapping(value = "/user/findAll/sort/lastactive", method = RequestMethod.GET)
    public List<UserInfoDTO> findAllByLastActive() throws LogisticException{
        return userInfoService.findAllByLastActive();
    }

    @RequestMapping(value = "/restpassword", method = RequestMethod.POST)
    public JsonResponse resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) throws LogisticException {
        return userInfoService.resetPassword(resetPasswordDTO);
    }

    @RequestMapping(value = "/forget/password", method = RequestMethod.POST)
    public void forgetPassword(@RequestParam("userEmail") String userEmail) throws LogisticException {
        userInfoService.forgetPassword(userEmail);
    }

}
