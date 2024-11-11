package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import com.bangbangbwa.backend.domain.streamer.common.mapper.DailyMessageMapper;
import org.springframework.stereotype.Component;

@Component
public class DailyMessageParser {

  public DailyMessage requestToEntity(CreateDailyMessageDto.Request request) {
    return DailyMessageMapper.INSTANCE.dtoToEntity(request);
  }
}
