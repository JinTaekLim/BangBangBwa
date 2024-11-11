package com.bangbangbwa.backend.domain.streamer.common.mapper;

import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DailyMessageMapper {

  DailyMessageMapper INSTANCE = Mappers.getMapper(DailyMessageMapper.class);

  @Mapping(target = "message", source = "request.message")
  DailyMessage dtoToEntity(CreateDailyMessageDto.Request request);

  @Mapping(target = "message", source = "dailyMessage.message")
  CreateDailyMessageDto.Response dtoToPromoteStreamerResponse(DailyMessage dailyMessage);
}
