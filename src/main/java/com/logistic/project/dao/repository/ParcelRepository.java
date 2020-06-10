package com.logistic.project.dao.repository;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {

    @Query("select pc from Parcel pc where pc.userId = :userId")
    List<Parcel> findParcelsByUserId(@Param("userId") Integer userId);

    @Query("select pc from Parcel pc where pc.id IN (:parcelIds)")
    List<Parcel> findByIds(@Param("parcelIds") List<Integer> parcelIds);

    @Query("select pc from Parcel pc where pc.userOrderId = :userOrderId")
    List<Parcel> findParcelsByUserOrderId(@Param("userOrderId") Integer userOrderId);
}
