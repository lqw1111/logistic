package com.logistic.project.service.Impl;

import com.logistic.project.controller.BaseController;
import com.logistic.project.dao.repository.*;
import com.logistic.project.dto.PaymentDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.entity.*;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.PaymentMapper;
import com.logistic.project.mapper.PromotionMapper;
import com.logistic.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private InvitationActivityRepository invitationActivityRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) throws LogisticException {
        Payment payment = PaymentMapper.INSTANCE.entity(paymentDTO);
        UserInfo userInfo = userInfoRepository.findById(payment.getUserId()).orElse(null);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }
        UserOrder userOrder = userOrderRepository.findUserOrderById(payment.getOrderId()).orElse(null);
        if (userOrder == null) {
            throw new LogisticException("User Order Doesn't Exist");
        }
        if (!userOrder.getUserId().equals(userInfo.getUid())) {
            throw new LogisticException("Invalid Payment Info");
        }

        List<Payment> payments = paymentRepository.findAllByUserIdAndOrderId(payment.getUserId(), payment.getOrderId());
        if (payments.size() != 0) {
            throw new LogisticException("Payment Already Processing");
        }

        Payment res = paymentRepository.save(payment);

        if (payment.getPromotionCode() != null) {
            promotionService.invalidatePromotion(payment.getPromotionCode(), userInfo.getUid());
        }


        PaymentDTO payDTO = PaymentMapper.INSTANCE.toDTO(res);
        if (payDTO.getPromotionCode() != null) {
            payDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payDTO.getPromotionCode())));
        }
        return payDTO;
    }

    @Override
    public PaymentDTO setActualPaid(Integer paymentId, BigDecimal actualPaid) throws LogisticException {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment == null) {
            throw new LogisticException("Payment Info Not Exist");
        }
        payment.setActualPaid(actualPaid);
        Payment res = paymentRepository.save(payment);
        PaymentDTO payDTO = PaymentMapper.INSTANCE.toDTO(res);
        if (payDTO.getPromotionCode() != null) {
            payDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payDTO.getPromotionCode())));
        }
        return payDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentDTO validatePayment(Integer paymentId) throws LogisticException {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment == null) {
            throw new LogisticException("Payment Info Not Exist");
        }
        if (payment.getActualPaid() == null) {
            throw new LogisticException("Doesn't Get Actual Paid");
        }
        if (payment.getPaid().compareTo(payment.getActualPaid()) != 0) {
            throw new LogisticException("Payment Not Equal");
        }
        payment.setValidate(true);
        Payment res = paymentRepository.save(payment);

        UserInfo userInfo = userInfoRepository.findById(payment.getUserId()).orElse(null);
        if (userInfo == null) {
            throw new LogisticException("User Doesn't Exist");
        }

        //验证支付之后自动更新包裹状态
        UserOrderDTO userOrderDTO = userOrderService.processingOrder(payment.getUserId(), payment.getOrderId());

        //TODO：发送邮件支付成功
        mailService.sendHtmlMail(userInfo.getEmail(), "支付成功", mailTemplateService.paymentSuccessEmail(userInfo, res, userOrderDTO.getOrderId()));

        PaymentDTO payDTO = PaymentMapper.INSTANCE.toDTO(res);
        if (payDTO.getPromotionCode() != null) {
            payDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payDTO.getPromotionCode())));
        }
        return payDTO;
    }

    @Override
    public List<PaymentDTO> findPaymentByUserId(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        if (!userInfoRepository.findById(userId).isPresent()) {
            throw new LogisticException("User Doesn't Exist");
        }
        List<Payment> payments = paymentRepository.findAllByUserId(userId);
        return payments.stream().map(payment -> {
            PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toDTO(payment);
            if (payment.getPromotionCode() != null) {
                paymentDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payment.getPromotionCode())));
            }
            return paymentDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> findAll() throws LogisticException {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> {
            PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toDTO(payment);
            if (payment.getPromotionCode() != null) {
                paymentDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payment.getPromotionCode())));
            }
            return paymentDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> findPaymentByUserIdWithActivity(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        if (!userInfoRepository.findById(userId).isPresent()) {
            throw new LogisticException("User Doesn't Exist");
        }
        List<Payment> payments = paymentRepository.findAllByUserId(userId);
        return payments.stream().map(payment -> {
            PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toDTO(payment);
            if (payment.getPromotionCode() != null) {
                paymentDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payment.getPromotionCode())));
            }
            Map<String, Object> map = new HashMap<>();
            map.put("payment", paymentDTO);
            Integer orderId = paymentDTO.getOrderId();
            InvitationActivity invitationActivity = invitationActivityRepository.findByOrderId(orderId);
            map.put("activity", invitationActivity);
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> findAllWithActivity() throws LogisticException {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> {
            PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toDTO(payment);
            if (payment.getPromotionCode() != null) {
                paymentDTO.setPromotion(PromotionMapper.INSTANCE.toDTO(promotionRepository.findByPromotionCode(payment.getPromotionCode())));
            }
            Map<String, Object> map = new HashMap<>();
            map.put("payment", paymentDTO);
            Integer orderId = paymentDTO.getOrderId();
            InvitationActivity invitationActivity = invitationActivityRepository.findByOrderId(orderId);
            map.put("activity", invitationActivity);
            return map;
        }).collect(Collectors.toList());
    }


}
