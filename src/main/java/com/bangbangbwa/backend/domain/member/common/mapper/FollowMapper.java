package com.bangbangbwa.backend.domain.member.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.ToggleFollowDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowMapper {

  FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

  @Mapping(target = "followeeId", source = "memberId")
  Follow dtoToEntity(ToggleFollowDto.Request dto);
}
