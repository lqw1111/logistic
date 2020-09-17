package com.logistic.project.controller;

import com.logistic.project.entity.PriceProfile;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PriceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/priceprofile")
public class PriceProfileController {

    @Autowired
    private PriceProfileService priceProfileService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PriceProfile> findAll() throws LogisticException {
        return priceProfileService.findAll();
    }
}
