package com.logistic.project.mapper;

import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.entity.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderHistoryMapper {
    OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);

    OrderHistory entity(OrderHistoryDTO orderHistoryDTO);

    OrderHistoryDTO toDTO(OrderHistory orderHistory);
}
