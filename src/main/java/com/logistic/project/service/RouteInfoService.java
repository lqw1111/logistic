package com.logistic.project.service;

import com.logistic.project.entity.RouteInfo;
import com.logistic.project.exception.LogisticException;

import java.util.List;

public interface RouteInfoService {

    public List<RouteInfo> findAll() throws LogisticException;
}
