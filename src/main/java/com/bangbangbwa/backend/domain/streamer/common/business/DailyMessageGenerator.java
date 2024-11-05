package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import com.bangbangbwa.backend.domain.streamer.common.entity.Streamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMessageGenerator {

  private final DailyMessageParser dailyMessageParser;

  public DailyMessage generate(CreateDailyMessageDto.Request request, Streamer streamer) {
    DailyMessage dailyMessage = dailyMessageParser.requestToEntity(request);
//    dailyMessage.updateStreamerId(streamer.getId());
    return dailyMessage;
  }

}
