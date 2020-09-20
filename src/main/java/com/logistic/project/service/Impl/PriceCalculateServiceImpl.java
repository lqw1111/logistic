package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.PriceProfileRepository;
import com.logistic.project.dao.repository.RouteInfoRepository;
import com.logistic.project.entity.PriceProfile;
import com.logistic.project.entity.RouteInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PriceCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceCalculateServiceImpl implements PriceCalculateService {

    private static final Integer HuananE = 5;

    @Autowired
    private RouteInfoRepository routeInfoRepository;

    @Autowired
    private PriceProfileRepository priceProfileRepository;

    @Override
    public List<Map<String, Object>> calculate(Integer countryId, BigDecimal length, BigDecimal hight, BigDecimal width, BigDecimal weight) throws LogisticException {
        BigDecimal volume = length.multiply(hight).multiply(width);
        List<Map<String, Object>> res = new ArrayList<>();

        List<RouteInfo> routeInfos = routeInfoRepository.findAll();

        for (RouteInfo routeInfo : routeInfos) {
            Map<String, Object> routePrice = new HashMap<>();
            BigDecimal volumeCoefficient = getCoefficient(routeInfo);
            BigDecimal volumeWeight = volume.divide(volumeCoefficient, 3);

            BigDecimal finalWeight = volumeWeight.max(weight);

            PriceProfile priceProfile = priceProfileRepository.findByCountryIdAndRouteIdAndWeight(countryId, routeInfo.getId(), finalWeight);
            if (priceProfile == null) {
                continue;
            }
            BigDecimal weightUnit = priceProfile.getMinimumWeight();
            finalWeight = finalWeight.divide(weightUnit).setScale(0, BigDecimal.ROUND_UP).multiply(weightUnit);

            routePrice.put("route", routeInfo.getName());
            //不足0.5
            if (finalWeight.compareTo(weightUnit) <= 0) {
                BigDecimal finalPrice = priceProfile.getFirstWeightPrice();
                routePrice.put("price", finalPrice.toString());
            } else { //超过0.5
                finalWeight = finalWeight.subtract(priceProfile.getMinimumWeight());
                BigDecimal unit = finalWeight.divide(weightUnit).setScale(0, BigDecimal.ROUND_UP);
                BigDecimal finalPrice = unit.multiply(priceProfile.getContinueWeightPrice()).add(priceProfile.getFirstWeightPrice());
                routePrice.put("price", finalPrice.toString());
            }
            res.add(routePrice);
        }
        return res;
    }

    private BigDecimal getCoefficient(RouteInfo routeInfo) {
        if (routeInfo.equals(HuananE)) {
            return new BigDecimal(6000);
        }
        return new BigDecimal(8000);
    }
}
