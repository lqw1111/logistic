package com.logistic.project.dao.repository;

import com.logistic.project.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {

    public List<OrderHistory> findAllByDeletedIsFalse();

    public OrderHistory findByIdAndDeletedIsFalse(Integer orderHistoryId);

    @Modifying
    @Transactional
    @Query("update OrderHistory o set o.deleted = 1 where o.id = :orderHistoryId")
    void deleteById(@Param("orderHistoryId") Integer orderHistoryId);
}
