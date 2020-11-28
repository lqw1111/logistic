package com.logistic.project.mapper;

import com.logistic.project.dto.PriceProfileDTO;
import com.logistic.project.entity.PriceProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceProfileMapper {

    PriceProfileMapper INSTANCE = Mappers.getMapper(PriceProfileMapper.class);

    PriceProfile entity(PriceProfileDTO priceProfileDTO);

    PriceProfileDTO toDTO(PriceProfile priceProfile);
}
