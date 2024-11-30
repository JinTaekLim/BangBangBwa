package com.bangbangbwa.backend.domain.member.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SummaryMapper {

  SummaryMapper INSTANCE = Mappers.getMapper(SummaryMapper.class);

  SummaryDto.Response dtoToResponse(SummaryDto dto);
}
