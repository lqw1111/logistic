package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Override
    public UserInfo insertUser(UserInfo userInfo) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsername(userInfo.getUsername());
        if (user != null) {
            throw new LogisticException("User Already Exist");
        }
        UserInfo info = userInfoRepository.save(userInfo);
        return info;
    }

    @Override
    public UserInfo updateUserByName(UserInfo userInfo) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsername(userInfo.getUsername());
        if (user == null)
            throw new LogisticException("User Doesn't Exist");
        UserInfo info = userInfoRepository.save(userInfo);
        return info;
    }

    @Override
    public List<UserInfo> findAllUser() throws LogisticException {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        return userInfos;
    }
}
