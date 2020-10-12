package com.logistic.project.service.Impl;

import com.logistic.project.entity.Payment;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.service.MailTemplateService;
import org.springframework.stereotype.Service;

@Service
public class MailTemplateServiceImpl implements MailTemplateService {

    @Override
    public String contructActiveEmail(UserInfo userInfo, String activeUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("您好").append(" ").append(userInfo.getUsername()).append(":").append("\n")
                .append("\n")
                .append("请点击链接激活账户").append(" ").append("\n")
                .append(activeUrl).append("\n").append("\n")
                .append("谢谢您的支持!").append("\n")
                .append("一闪团队");
        return sb.toString();
    }

    @Override
    public String constructContent(UserInfo userInfo, String newPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append("您好").append(" ").append(userInfo.getUsername()).append(":").append("\n")
                .append("\n")
                .append("您").append(" ").append(userInfo.getUsername()).append(" 忘记了密码.").append("\n")
                .append("密码重置为 ").append(newPassword).append("\n").append("\n")
                .append("谢谢您的支持!").append("\n")
                .append("一闪团队");
        return sb.toString();
    }

    @Override
    public String paymentSuccessEmail(UserInfo userInfo, Payment payment) {
        StringBuilder sb = new StringBuilder();
        sb.append("您好").append(" ").append(userInfo.getUsername()).append(":").append("\n")
                .append("\n")
                .append("您的订单：").append(payment.getOrderId()).append("(Order ID)").append(" 已经支付.").append("\n")
                .append("订单价格为： ").append(payment.getPrice()).append("\n")
                .append("优惠券： ").append(payment.getPromotionCode() == null ? "N/A" : payment.getPromotionCode()).append("\n")
                .append("您支付金额为： ").append(payment.getPaid()).append("\n")
                .append("请您发送支付截图给客服微信，方便核实金额数目.").append("\n")
                .append("客户核实相应金额后会与您联系,有任何问题请即使与客服联系").append("\n").append("\n")
                .append("谢谢您的支持!").append("\n")
                .append("一闪团队");
        return sb.toString();
    }


}
