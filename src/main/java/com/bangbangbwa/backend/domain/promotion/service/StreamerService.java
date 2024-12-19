package com.bangbangbwa.backend.domain.promotion.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.platform.business.PlatformRelation;
import com.bangbangbwa.backend.domain.platform.business.PlatformValidator;
import com.bangbangbwa.backend.domain.platform.common.dto.StreamerPlatformDto;
import com.bangbangbwa.backend.domain.promotion.business.RandomStreamerProvider;
import com.bangbangbwa.backend.domain.promotion.business.StreamerReader;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.streamer.common.dto.AddPlatformDto.Request;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamerService {

  private final RandomStreamerProvider randomStreamerProvider;
  private final MemberProvider memberProvider;
  private final StreamerReader streamerReader;
  private final PlatformRelation platformRelation;
  private final PlatformValidator platformValidator;

  public Set<PromotionStreamerDto.PromotionStreamer> getRandomStreamers() {
    return randomStreamerProvider.getStreamers(memberProvider.getCurrentMemberIdOrNull());
  }

  public void addStreamerPlatform(Request request) {
    Long memberId = memberProvider.getCurrentMemberId();
    Streamer streamer = streamerReader.findByMemberId(memberId);
    StreamerPlatformDto dto = new StreamerPlatformDto(streamer.getId(), request.platformId(),
        request.platformProfileUrl());
    platformRelation.relation(dto);
  }

  public void removeStreamerPlatform(Long platformId) {
    Long memberId = memberProvider.getCurrentMemberId();
    Long streamerId = streamerReader.findByMemberId(memberId).getId();
    platformValidator.validateStreamerPlatform(streamerId, platformId);
    StreamerPlatformDto dto = new StreamerPlatformDto(streamerId, platformId, null);
    platformRelation.breakRelation(dto);
  }
}
