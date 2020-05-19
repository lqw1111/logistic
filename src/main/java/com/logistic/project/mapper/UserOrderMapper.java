package com.logistic.project.mapper;

import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.entity.UserOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserOrderMapper {
    UserOrderMapper INSTANCE = Mappers.getMapper(UserOrderMapper.class);

    UserOrder entity(UserOrderDTO userOrderDTO);

    UserOrderDTO toDTO(UserOrder userOrder);
}
