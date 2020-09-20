package com.logistic.project.controller;

import com.logistic.project.dto.PriceCalculateRequestDTO;
import com.logistic.project.service.PriceCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calculate")
public class PriceCalculateController {

    @Autowired
    private PriceCalculateService priceCalculateService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<Map<String, Object>> calculate(@RequestBody PriceCalculateRequestDTO priceCalculateRequestDTO) throws Exception{
        return priceCalculateService.calculate(priceCalculateRequestDTO.getCountryId(),
                priceCalculateRequestDTO.getLength(), priceCalculateRequestDTO.getHight(),
                priceCalculateRequestDTO.getWidth(),
                priceCalculateRequestDTO.getWeight());
    }
}
