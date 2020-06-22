package com.logistic.project.dao.repository;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {

    @Query("select pc from Parcel pc where pc.userId = :userId and pc.deleted = 0")
    List<Parcel> findParcelsByUserId(@Param("userId") Integer userId);

    @Query("select pc from Parcel pc where pc.id IN (:parcelIds) and pc.deleted = 0")
    List<Parcel> findByIds(@Param("parcelIds") List<Integer> parcelIds);

    @Query("select pc from Parcel pc where pc.userOrderId = :userOrderId and pc.deleted = 0")
    List<Parcel> findParcelsByUserOrderId(@Param("userOrderId") Integer userOrderId);

    @Modifying
    @Transactional
    @Query("update Parcel p set p.deleted = 1 where p.id = :parcelId")
    void deleteParcelById(@Param("parcelId") int id);

    @Query("select p from Parcel p where p.id = :parcelId and p.deleted = 0")
    Optional<Parcel> findParcelById(@Param("parcelId") Integer parcelId);
}
