package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.admin.common.dto.GetPendingMembers.GetPendingMemberResponse;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingStreamerReader {

  private final PendingStreamerRepository pendingStreamerRepository;

  public List<GetPendingMemberResponse> findByPendingMembers() {
    return pendingStreamerRepository.findByPendingMembers();
  }

}
