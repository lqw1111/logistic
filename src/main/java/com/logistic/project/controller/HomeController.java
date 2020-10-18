package com.logistic.project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController extends BaseController{

    @PreAuthorize("hasAnyRole('admin', 'user')")
    @RequestMapping("/home")
    public String home(){
        return getPrincipal();
    }
}
