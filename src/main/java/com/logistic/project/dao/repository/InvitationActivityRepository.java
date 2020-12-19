package com.logistic.project.dao.repository;

import com.logistic.project.entity.InvitationActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InvitationActivityRepository extends JpaRepository<InvitationActivity, Integer> {

    @Query("SELECT activity FROM InvitationActivity activity WHERE activity.orderCode = :orderCode and activity.deleted = 0")
    InvitationActivity findByOrderCode(@Param("orderCode") String orderCode);

    @Transactional
    @Modifying
    @Query("UPDATE InvitationActivity activity SET activity.deleted = 1 WHERE activity.id = :id")
    void deleteById(@Param("id") Integer id);

    @Query("SELECT activity FROM InvitationActivity activity WHERE activity.orderId = :orderId and activity.deleted = 0")
    InvitationActivity findByOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT activity FROM InvitationActivity activity WHERE activity.userId = :userId and activity.deleted = 0")
    List<InvitationActivity> findByUserId(@Param("userId") Integer userId);
}
