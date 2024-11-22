package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import com.bangbangbwa.backend.domain.streamer.repository.DailyMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyMessageReader {

  private final DailyMessageRepository dailyMessageRepository;

  public List<DailyMessage> findByIds(List<Long> idList) {
    return dailyMessageRepository.findByIds(idList);
  }

}
