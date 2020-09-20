package com.logistic.project.mapper;

import com.logistic.project.dto.CountryDTO;
import com.logistic.project.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    Country entity(CountryDTO countryDTO);

    CountryDTO toDTO(Country country);
}
