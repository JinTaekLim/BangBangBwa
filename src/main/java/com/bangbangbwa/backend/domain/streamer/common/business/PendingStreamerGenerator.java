package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.admin.common.dto.ApproveStreamerDto;
import com.bangbangbwa.backend.domain.admin.common.entity.Admin;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingStreamerGenerator {

  private final PendingStreamerParser pendingStreamerParser;

  public PendingStreamer generate(PromoteStreamerDto.Request request, Long memberId) {
    PendingStreamer pendingStreamer = pendingStreamerParser.requestToEntity(request);
    pendingStreamer.updateMemberId(memberId);
    return pendingStreamer;
  }

  public PendingStreamer updateAdminId(ApproveStreamerDto.Request request, Admin admin) {
    PendingStreamer pendingStreamer = pendingStreamerParser.requestToEntity(request);
    pendingStreamer.updateAdminId(admin.getId());
    return pendingStreamer;
  }

}
