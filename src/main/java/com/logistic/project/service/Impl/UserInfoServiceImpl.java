package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.UserInfoMapper;
import com.logistic.project.service.MailService;
import com.logistic.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private MailService mailService;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Override
    public UserInfoDTO insertUser(UserInfoDTO userInfoDTO) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsername(userInfoDTO.getUsername());
        if (user != null) {
            throw new LogisticException("User Already Exist");
        }
        UserInfo entity = UserInfoMapper.INSTANCE.entity(userInfoDTO);
        entity.setRole(UserInfo.Role.user);
        entity.setPassword(encryptPassword(entity.getPassword()));
        UserInfo info = userInfoRepository.save(entity);
        UserInfoDTO res = UserInfoMapper.INSTANCE.toDTO(info);
        res.setPassword(null);

        //TODO: add user email confirmation
        mailService.sendTextMail(res.getEmail(),"测试文本邮箱发送","你好你好！");
        return res;
    }

    private String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserInfoDTO updateUserByName(UserInfoDTO userInfoDTO) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsername(userInfoDTO.getUsername());
        if (user == null)
            throw new LogisticException("User Doesn't Exist");
        UserInfo entity = UserInfoMapper.INSTANCE.entity(userInfoDTO);
        UserInfo info = userInfoRepository.save(entity);
        UserInfoDTO res = UserInfoMapper.INSTANCE.toDTO(info);
        return res;
    }

    @Override
    public List<UserInfoDTO> findAllUser() throws LogisticException {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        List<UserInfoDTO> res = userInfos.stream()
                .map(userInfo -> {return UserInfoMapper.INSTANCE.toDTO(userInfo);})
                .collect(Collectors.toList());
        return res;
    }
}
