package com.bangbangbwa.backend.domain.member.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {

  ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

  ProfileDto.Response dtoToResponse(ProfileDto dto);
}
