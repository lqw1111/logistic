package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.CountryRepository;
import com.logistic.project.dao.repository.PriceProfileRepository;
import com.logistic.project.dao.repository.RouteInfoRepository;
import com.logistic.project.dto.PriceProfileDTO;
import com.logistic.project.entity.PriceProfile;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.PriceProfileMapper;
import com.logistic.project.service.PriceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceProfileServiceImpl implements PriceProfileService {

    @Autowired
    private PriceProfileRepository priceProfileRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RouteInfoRepository routeInfoRepository;

    @Override
    public List<PriceProfileDTO> findAll() throws LogisticException {
        List<PriceProfile> list = priceProfileRepository.findAll();
        return list.stream().map(priceProfile -> {
            PriceProfileDTO priceProfileDTO = PriceProfileMapper.INSTANCE.toDTO(priceProfile);
            priceProfileDTO.setCountry(countryRepository.findById(priceProfile.getCountryId()).get());
            priceProfileDTO.setRouteInfo(routeInfoRepository.findById(priceProfile.getRouteId()).get());
            return priceProfileDTO;
        }).collect(Collectors.toList());
    }
}
