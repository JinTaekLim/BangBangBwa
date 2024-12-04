package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMessageGenerator {

  private final DailyMessageParser dailyMessageParser;

  public DailyMessage generate(CreateDailyMessageDto.Request request, Long memberId) {
    DailyMessage dailyMessage = dailyMessageParser.requestToEntity(request);
    dailyMessage.updateMemberId(memberId);
    return dailyMessage;
  }

}
