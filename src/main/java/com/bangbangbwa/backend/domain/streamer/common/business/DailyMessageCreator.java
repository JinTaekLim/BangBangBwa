package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import com.bangbangbwa.backend.domain.streamer.repository.DailyMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMessageCreator {

  private final DailyMessageRepository dailyMessageRepository;

  public void save(DailyMessage dailyMessage) {
    dailyMessageRepository.save(
        dailyMessage.getMemberId(),
        dailyMessage.getMessage(),
        dailyMessage.getExpirationTime()
    );
  }

}
