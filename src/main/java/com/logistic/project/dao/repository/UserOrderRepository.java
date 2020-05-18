package com.logistic.project.dao.repository;

import com.logistic.project.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

    @Query(value = "select * from user_order where user_id=?1", nativeQuery = true)
    List<UserOrder> findByUserId(Integer userId);

    @Query(value = "select * from user_order where user_id=?1 and id=?2", nativeQuery = true)
    UserOrder findByUserIdAndOrderId(Integer userId, Integer userOrderId);
}
