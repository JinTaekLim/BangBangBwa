package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingStreamerUpdater {

  private final PendingStreamerRepository pendingStreamerRepository;

  public void updateAdminId(PendingStreamer pendingStreamer) {
    pendingStreamerRepository.updateStatus(pendingStreamer);
  }

}
