package com.logistic.project.mapper;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.dto.PromotionDTO;
import com.logistic.project.entity.Parcel;
import com.logistic.project.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromotionMapper {
    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    Promotion entity(PromotionDTO promotionDTO);

    PromotionDTO toDTO(Promotion promotion);
}
