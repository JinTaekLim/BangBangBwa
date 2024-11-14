package com.bangbangbwa.backend.domain.streamer.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.streamer.common.business.PendingStreamerCreator;
import com.bangbangbwa.backend.domain.streamer.common.business.PendingStreamerGenerator;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PendingStreamerService {

  private final MemberProvider memberProvider;
  private final PendingStreamerGenerator pendingStreamerGenerator;
  private final PendingStreamerCreator pendingStreamerCreator;

  public PendingStreamer promoteStreamer(PromoteStreamerDto.Request request) {
    Member member = memberProvider.getCurrentMember();
    PendingStreamer pendingStreamer = pendingStreamerGenerator.generate(request, member);
    pendingStreamerCreator.save(pendingStreamer);
    return pendingStreamer;
  }
}
