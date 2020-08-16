package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.enumeration.Role;
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
    public UserInfoDTO insertUser(UserInfo userInfo) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsername(userInfo.getUsername());
        if (user != null) {
            throw new LogisticException("User Already Exist");
        }
        UserInfo entity = userInfo;
        entity.setRole(Role.user);
        entity.setPassword(encryptPassword(entity.getPassword()));
        entity.setDeleted(false);
        UserInfo info = userInfoRepository.save(entity);
        UserInfoDTO res = UserInfoMapper.INSTANCE.toDTO(info);
        res.setPassword(null);

        //TODO: 实现发送确认邮件的逻辑
//        mailService.sendTextMail(res.getEmail(),"测试文本邮箱发送","你好你好！");

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
        user.setRole(userInfoDTO.getRole());
        user.setAddress(userInfoDTO.getAddress());
        user.setEmail(userInfoDTO.getEmail());
        user.setPhone(userInfoDTO.getPhone());
        user.setWxId(userInfoDTO.getWxId());

        UserInfo info = userInfoRepository.save(user);
        UserInfoDTO res = UserInfoMapper.INSTANCE.toDTO(info);

        res.setPassword(null);
        return res;
    }

    @Override
    public List<UserInfoDTO> findAllUser() throws LogisticException {
        List<UserInfo> userInfos = userInfoRepository.findAllByDeletedIsFalse();
        List<UserInfoDTO> res = userInfos.stream()
                .map(userInfo -> {
                    UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toDTO(userInfo);
                    userInfoDTO.setPassword(null);
                    return userInfoDTO;
                })
                .collect(Collectors.toList());
        return res;
    }

    @Override
    public UserInfoDTO getUserInfo(String username) throws LogisticException{
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo == null)
            throw new LogisticException("User Doesn't Exist");
        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toDTO(userInfo);
        userInfoDTO.setPassword(null);
        return userInfoDTO;
    }

    @Override
    public List<UserInfoDTO> findAllByLastActive() throws LogisticException {
        List<UserInfo> userInfos = userInfoRepository.findAllByDeletedIsFalse();
        userInfos.sort((u1, u2) -> {return u2.getLastActiveTime().compareTo(u1.getLastActiveTime());});
        List<UserInfoDTO> res = userInfos.stream()
                .map(userInfo -> {
                    UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toDTO(userInfo);
                    userInfoDTO.setPassword(null);
                    return userInfoDTO;
                })
                .collect(Collectors.toList());
        return res;
    }
}
