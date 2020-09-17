package com.logistic.project.controller;

import com.logistic.project.entity.RouteInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.RouteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/routeinfo")
public class RouteInfoController {

    @Autowired
    private RouteInfoService routeInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<RouteInfo> findAll() throws LogisticException {
        return routeInfoService.findAll();
    }
}
