package com.logistic.project.dao.repository;

import com.logistic.project.entity.PriceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PriceProfileRepository extends JpaRepository<PriceProfile, Integer> {

    @Query("select pp from PriceProfile pp where pp.countryId = :countryId " +
            "and pp.routeId = :routeId and pp.weightRangeLow <= :weight and pp.weightRangeHigh >= :weight")
    PriceProfile findByCountryIdAndRouteIdAndWeight(@Param("countryId") Integer countryId,
                                                    @Param("routeId") Integer routeId,
                                                    @Param("weight")BigDecimal weight);
}
