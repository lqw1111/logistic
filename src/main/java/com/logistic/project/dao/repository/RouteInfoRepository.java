package com.logistic.project.dao.repository;

import com.logistic.project.entity.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteInfoRepository extends JpaRepository<RouteInfo, Integer> {
}
