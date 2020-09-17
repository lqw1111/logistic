package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.RouteInfoRepository;
import com.logistic.project.entity.RouteInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.RouteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteInfoServiceImpl implements RouteInfoService {

    @Autowired
    private RouteInfoRepository routeInfoRepository;

    @Override
    public List<RouteInfo> findAll() throws LogisticException {
        return routeInfoRepository.findAll();
    }
}
