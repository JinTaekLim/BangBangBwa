package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingStreamerCreator {

  private final PendingStreamerRepository pendingStreamerRepository;

  public void save(PendingStreamer pendingStreamer) {
    pendingStreamerRepository.save(pendingStreamer);
  }

}
