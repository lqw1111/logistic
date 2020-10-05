package com.logistic.project.dao.repository;

import com.logistic.project.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    List<Promotion> findAllByUserIdAndValidateIsTrue(Integer userId);

    Promotion findByPromotionCode(String promotionCode);
}
