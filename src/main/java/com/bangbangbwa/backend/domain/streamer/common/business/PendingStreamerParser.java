package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.common.mapper.PendingStreamerMapper;
import org.springframework.stereotype.Component;

@Component
public class PendingStreamerParser {

  public PendingStreamer requestToEntity(PromoteStreamerDto.Request request) {
    return PendingStreamerMapper.INSTANCE.dtoToEntity(request);
  }
}
