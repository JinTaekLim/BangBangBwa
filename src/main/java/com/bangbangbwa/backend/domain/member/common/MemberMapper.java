package com.bangbangbwa.backend.domain.member.common;

import com.bangbangbwa.backend.domain.member.common.MemberSignupDto.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  @Mapping(target = "nickname", source = "request.nickname")
//  @Mapping(target = "interests", source = "request.interests")
  Member dtoToEntity(Request request);
}
