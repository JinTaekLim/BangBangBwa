package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class MemberProcessor {

  public void removeDataIfAnotherMember(SummaryDto dto, Long memberId, Long currentMemberId) {
    // 다른 사용자를 조회할 경우 불필요한 정보는 제거한다.
    if (!Objects.equals(memberId, currentMemberId)) {
      dto.setFollowingCount(0L);
      dto.setIsSubmittedToStreamer(false);
    }
  }

}
