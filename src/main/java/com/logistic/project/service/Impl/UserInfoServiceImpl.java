package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dto.JsonResponse;
import com.logistic.project.dto.ResetPasswordDTO;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.enumeration.Role;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.UserInfoMapper;
import com.logistic.project.service.MailService;
import com.logistic.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Value("${company.main.page}")
    public String mainPage;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private MailService mailService;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDTO insertUser(UserInfo userInfo) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsernameAndEmail(userInfo.getUsername(), userInfo.getEmail());
        if (user != null) {
            throw new LogisticException("User Already Exist");
        }
        UserInfo entity = userInfo;
        entity.setRole(Role.user);
        entity.setPassword(encryptPassword(entity.getPassword()));
        entity.setDeleted(false);

        String token = UUID.randomUUID().toString();
        entity.setToken(token);
        entity.setActive(false);

        String activeUrl = mainPage + "?email=" + userInfo.getEmail() + "&token=" + token + "&username=" + userInfo.getUsername();
        mailService.sendTextMail(userInfo.getEmail(),"激活邮箱", contructActiveEmail(entity, activeUrl));

        UserInfo info = userInfoRepository.save(entity);
        UserInfoDTO res = UserInfoMapper.INSTANCE.toDTO(info);
        res.setPassword(null);

        return res;
    }

    private String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserInfoDTO updateUserByName(UserInfoDTO userInfoDTO) throws LogisticException {
        UserInfo user = userInfoRepository.findByUsername(userInfoDTO.getUsername());
        if (user == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        UserInfo userInfo = userInfoRepository.findByEmailAndDeletedIsFalse(userInfoDTO.getEmail());
        if (userInfo != null && !user.getUid().equals(userInfo.getUid())) {
            throw new LogisticException("Email Already Registed");
        }
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void forgetPassword(String userEmail) throws LogisticException {
        UserInfo user = userInfoRepository.findByEmailAndDeletedIsFalse(userEmail);
        if (user == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        String newPassword = getRandomString(8);
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userInfoRepository.save(user);
        mailService.sendTextMail(userEmail, "忘记密码", constructContent(user, newPassword));
    }

    @Override
    public JsonResponse resetPassword(ResetPasswordDTO resetPasswordDTO) throws LogisticException {
        UserInfo userInfo = userInfoRepository.findByUsernameAndEmail(resetPasswordDTO.getUsername(), resetPasswordDTO.getEmail());
        if(userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(resetPasswordDTO.getOldPassword(), userInfo.getPassword())
                || resetPasswordDTO.getOldPassword().equals(resetPasswordDTO.getNewPassword())) {
            throw new LogisticException("Password Error");
        }
        userInfo.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        userInfoRepository.save(userInfo);
        JsonResponse res = new JsonResponse();
        res.succ().message("Update Success");
        return res;
    }

    @Override
    public void activeAccount(String userEmail, String token, String userName) throws LogisticException{
        UserInfo userInfo = userInfoRepository.findByUsernameAndEmail(userName, userEmail);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        if (userInfo.getToken().equals(token)) {
            userInfo.setActive(true);
            userInfoRepository.save(userInfo);
        } else {
            throw new LogisticException("Active Fail");
        }
    }

    private String constructContent(UserInfo userInfo, String newPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append("您好").append(" ").append(userInfo.getUsername()).append(":").append("\n")
                .append("\n")
                .append("您").append(" ").append(userInfo.getUsername()).append(" 忘记了密码.").append("\n")
                .append("密码重置为 ").append(newPassword).append("\n").append("\n")
                .append("谢谢您的支持!").append("\n")
                .append("一闪团队");
        return sb.toString();
    }

    private String contructActiveEmail(UserInfo userInfo, String activeUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("您好").append(" ").append(userInfo.getUsername()).append(":").append("\n")
                .append("\n")
                .append("请点击链接激活账户").append(" ").append("\n")
                .append(activeUrl).append("\n").append("\n")
                .append("谢谢您的支持!").append("\n")
                .append("一闪团队");
        return sb.toString();
    }

    public String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < length ; i++){
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
