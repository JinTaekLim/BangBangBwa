package com.bangbangbwa.backend.domain.streamer.service;

import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageCreator;
import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageGenerator;
import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageReader;
import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyMessageService {

  private final DailyMessageGenerator dailyMessageGenerator;
  private final DailyMessageCreator dailyMessageCreator;
  private final DailyMessageReader dailyMessageReader;

  public DailyMessage createDailyMessage(CreateDailyMessageDto.Request request) {
    // streamer 관련 추가 작업 필요
    DailyMessage dailyMessage = dailyMessageGenerator.generate(request,null);
    dailyMessageCreator.save(dailyMessage);
    return dailyMessage;
  }

  public List<DailyMessage> getDailyMessagesByIds(List<Long> ids) {
    return dailyMessageReader.findByIds(ids);
  }

}
