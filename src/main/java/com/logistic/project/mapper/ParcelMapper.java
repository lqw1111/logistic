package com.logistic.project.mapper;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelMapper {

    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    Parcel entity(ParcelDTO parcelDTO);

    ParcelDTO toDTO(Parcel parcel);
}
