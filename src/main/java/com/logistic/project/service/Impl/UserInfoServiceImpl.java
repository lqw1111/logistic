package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.*;
import com.logistic.project.dto.JsonResponse;
import com.logistic.project.dto.ResetPasswordDTO;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.*;
import com.logistic.project.enumeration.Role;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.UserInfoMapper;
import com.logistic.project.service.MailService;
import com.logistic.project.service.MailTemplateService;
import com.logistic.project.service.UserInfoService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private InvitationActivityRepository invitationActivityRepository;

    @Autowired
    private InvitationActivityUserRepository invitationActivityUserRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private final static String ORDER_PROMOTION_START = "O:";

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDTO insertUser(UserInfo userInfo) throws LogisticException {
        List<UserInfo> users = userInfoRepository.findByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail());
        if (users.size() > 0) {
            throw new LogisticException("User Already Exist");
        }
        UserInfo entity = userInfo;
        entity.setRole(Role.user);
        entity.setPassword(encryptPassword(entity.getPassword()));
        entity.setDeleted(false);

        String token = UUID.randomUUID().toString();
        entity.setToken(token);
        entity.setActive(false);

        if (userInfo.getInvitedCode() != null) {
            String invitedCode = userInfo.getInvitedCode();
            UserInfo user = userInfoRepository.findByToken(invitedCode);
            if (user == null) {
                throw new LogisticException("Invited User Not Exist");
            }
            entity.setInvitedBy(user.getUid());
        }

        if (userInfo.getOrderCode() != null) {
            String orderCode = userInfo.getOrderCode();
            InvitationActivity invitationActivity = invitationActivityRepository.findByOrderCode(orderCode);
            if (invitationActivity == null) {
                throw new LogisticException("Activity Not Exist");
            }
            UserInfo invitedUser = userInfoRepository.findById(invitationActivity.getUserId()).orElse(null);
            if (invitedUser == null) {
                throw new LogisticException("Invited User Not Exist");
            }

            //用户数加1， 用户email添加，保存
            invitationActivity.setInvitedUserNum(invitationActivity.getInvitedUserNum() + 1);
            if (invitationActivity.getInvitedUserEmail() == null || "".equals(invitationActivity.getInvitedUserEmail())) {
                invitationActivity.setInvitedUserEmail(userInfo.getEmail());
            } else {
                invitationActivity.setInvitedUserEmail(invitationActivity.getInvitedUserEmail() + ":" + userInfo.getEmail());
            }

            invitationActivityRepository.save(invitationActivity);

            //payment价格降低
//            invitedUserPayment.setPaid(invitedUserPayment.getPaid().subtract(invitationActivity.getPerUserDiscountPrice().multiply(BigDecimal.valueOf(invitationActivity.getInvitedUserNum()))));
//            paymentRepository.save(invitedUserPayment);

            entity.setInvitedCode(invitedUser.getToken());
            entity.setInvitedBy(invitedUser.getUid());
        }

        String activeUrl = mainPage + "/email=" + userInfo.getEmail() + "&token=" + token + "&username=" + userInfo.getUsername();
        mailService.sendHtmlMail(userInfo.getEmail(),"激活邮箱", mailTemplateService.contructActiveEmail(entity, activeUrl));

        UserInfo info = userInfoRepository.save(entity);
        UserInfoDTO res = UserInfoMapper.INSTANCE.toDTO(info);
        res.setPassword(null);

        //记录是谁砍了这个order
        if (userInfo.getOrderCode() != null) {
            InvitationActivityUser invitationActivityUser = new InvitationActivityUser();
            String orderCode = userInfo.getOrderCode();
            invitationActivityUser.setUserId(info.getUid());
            invitationActivityUser.setOrderCode(orderCode);
            invitationActivityUserRepository.save(invitationActivityUser);
        }

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
        mailService.sendHtmlMail(userEmail, "忘记密码", mailTemplateService.constructContent(user, newPassword));
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
    public ResponseEntity<Object> activeAccount(String userEmail, String token, String userName) throws LogisticException{
        UserInfo userInfo = userInfoRepository.findByUsernameAndEmail(userName, userEmail);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        if (userInfo.isActive()) {
            throw new LogisticException("User Already Active");
        }
        if (userInfo.getToken().equals(token)) {
            userInfo.setActive(true);
            userInfoRepository.save(userInfo);
            return ResponseEntity.ok().body("success");
        } else {
            throw new LogisticException("Active Fail");
        }
    }

    @Override
    public void isActive(String username) throws LogisticException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (!userInfo.isActive()) {
            throw new LogisticException("Account Need Active");
        }
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
