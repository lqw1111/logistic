package com.logistic.project.dao.repository;

import com.logistic.project.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

    @Query(value = "select * from user_order where user_id=?1 and deleted = 0", nativeQuery = true)
    List<UserOrder> findByUserId(Integer userId);

    @Query(value = "select * from user_order where user_id=?1 and id=?2 and deleted = 0", nativeQuery = true)
    UserOrder findByUserIdAndOrderId(Integer userId, Integer userOrderId);

    @Modifying
    @Transactional
    @Query(value = "update UserOrder uo set uo.deleted = 1 where uo.id = :orderId")
    void deleteUserOrderById(@Param("orderId") Integer orderId);

    @Query("select uo from UserOrder uo where uo.id = :userOrderId and uo.deleted = 0")
    Optional<UserOrder> findUserOrderById(@Param("userOrderId") Integer userOrderId);
}
