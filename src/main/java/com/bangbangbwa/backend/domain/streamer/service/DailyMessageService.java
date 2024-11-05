package com.bangbangbwa.backend.domain.streamer.service;

import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageCreator;
import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageGenerator;
import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyMessageService {

  private final DailyMessageGenerator dailyMessageGenerator;
  private final DailyMessageCreator dailyMessageCreator;

  public DailyMessage createDailyMessage(CreateDailyMessageDto.Request request) {
    // streamer 관련 추가 작업 필요
    DailyMessage dailyMessage = dailyMessageGenerator.generate(request,null);
    dailyMessageCreator.save(dailyMessage);
    return dailyMessage;
  }

}
