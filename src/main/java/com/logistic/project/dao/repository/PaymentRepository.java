package com.logistic.project.dao.repository;

import com.logistic.project.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByUserId(Integer userId);

    List<Payment> findAllByUserIdAndOrderId(Integer userId, Integer orderId);
}
