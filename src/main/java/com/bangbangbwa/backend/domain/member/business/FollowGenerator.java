package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.ToggleFollowDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowGenerator {

  private final FollowParser followParser;
  private final FollowUpdater followUpdater;

  public Follow generate(ToggleFollowDto.Request request, Long memberId) {
    Follow follow = followParser.requestToEntity(request);
    followUpdater.updateFollower(follow, memberId);
    return follow;
  }
}
