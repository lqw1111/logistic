package com.logistic.project.controller;

import com.logistic.project.dto.PriceCalculateRequestDTO;
import com.logistic.project.service.PriceCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
                priceCalculateRequestDTO.getLength() == null ? new BigDecimal(0.1) : priceCalculateRequestDTO.getLength(),
                priceCalculateRequestDTO.getHight() == null ? new BigDecimal(0.1) : priceCalculateRequestDTO.getHight(),
                priceCalculateRequestDTO.getWidth() == null ? new BigDecimal(0.1) : priceCalculateRequestDTO.getWidth(),
                priceCalculateRequestDTO.getWeight());
    }
}
