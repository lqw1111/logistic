package com.logistic.project.mapper;

import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfo entity(UserInfoDTO userInfoDTO);

    UserInfoDTO toDTO(UserInfo userInfo);
}
