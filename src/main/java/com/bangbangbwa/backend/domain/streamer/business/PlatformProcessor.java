package com.bangbangbwa.backend.domain.streamer.business;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.promotion.business.StreamerReader;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformProcessor {

  private final MemberProvider memberProvider;
  private final StreamerReader streamerReader;

  public void setPlatforms(SummaryDto summaryDto, Long memberId) {
    if (Objects.equals(memberProvider.getCurrentRoleOrNull(), Role.STREAMER)) {
      summaryDto.setPlatforms(streamerReader.findStreamerPlatforms(memberId));
    }
  }
}
