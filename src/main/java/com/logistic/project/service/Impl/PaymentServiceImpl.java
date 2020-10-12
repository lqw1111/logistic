package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.PaymentRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.PaymentDTO;
import com.logistic.project.entity.OrderStatus;
import com.logistic.project.entity.Payment;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.PaymentMapper;
import com.logistic.project.service.MailService;
import com.logistic.project.service.MailTemplateService;
import com.logistic.project.service.PaymentService;
import com.logistic.project.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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

        //TODO：发送邮件支付成功
        mailService.sendTextMail(userInfo.getEmail(), "支付成功", mailTemplateService.paymentSuccessEmail(userInfo, res));

        return PaymentMapper.INSTANCE.toDTO(res);
    }

    @Override
    public PaymentDTO setActualPaid(Integer paymentId, BigDecimal actualPaid) throws LogisticException {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment == null) {
            throw new LogisticException("Payment Info Not Exist");
        }
        payment.setActualPaid(actualPaid);
        Payment res = paymentRepository.save(payment);
        return PaymentMapper.INSTANCE.toDTO(res);
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

        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(payment.getUserId(), payment.getOrderId());
        userOrder.setStatusId(OrderStatus.PROCESSING);
        userOrderRepository.save(userOrder);

        return PaymentMapper.INSTANCE.toDTO(res);
    }

    @Override
    public List<PaymentDTO> findPaymentByUserId(Integer userId) throws LogisticException {
        if (!userInfoRepository.findById(userId).isPresent()) {
            throw new LogisticException("User Doesn't Exist");
        }
        List<Payment> payments = paymentRepository.findAllByUserId(userId);
        return payments.stream().map(payment -> PaymentMapper.INSTANCE.toDTO(payment)).collect(Collectors.toList());
    }


}
