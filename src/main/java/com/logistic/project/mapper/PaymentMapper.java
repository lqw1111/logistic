package com.logistic.project.mapper;

import com.logistic.project.dto.PaymentDTO;
import com.logistic.project.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment entity(PaymentDTO paymentDTO);

    PaymentDTO toDTO(Payment payment);
}
