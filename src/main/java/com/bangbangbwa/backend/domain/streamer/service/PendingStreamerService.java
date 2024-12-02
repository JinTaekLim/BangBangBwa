package com.bangbangbwa.backend.domain.streamer.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.sns.business.PromoteValidator;
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
  private final PromoteValidator promoteValidator;

  public PendingStreamer promoteStreamer(PromoteStreamerDto.Request request) {
    Long memberId = memberProvider.getCurrentMemberId();
    promoteValidator.checkForDuplicatePendingPromotion(memberId);
    PendingStreamer pendingStreamer = pendingStreamerGenerator.generate(request, memberId);
    pendingStreamerCreator.save(pendingStreamer);
    return pendingStreamer;
  }
}
