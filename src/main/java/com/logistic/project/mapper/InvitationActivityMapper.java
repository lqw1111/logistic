package com.logistic.project.mapper;

import com.logistic.project.dto.CountryDTO;
import com.logistic.project.dto.InvitationActivityDTO;
import com.logistic.project.entity.Country;
import com.logistic.project.entity.InvitationActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvitationActivityMapper {

    InvitationActivityMapper INSTANCE = Mappers.getMapper(InvitationActivityMapper.class);

    InvitationActivity entity(InvitationActivityDTO invitationActivityDTO);

    InvitationActivityDTO toDTO(InvitationActivity invitationActivity);
}
