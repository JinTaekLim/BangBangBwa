package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowResponse;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto.FollowerResponse;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowReader {

  private final FollowRepository followRepository;

  public List<FollowerResponse> findFollowersByMemberId(Long memberId) {
    return followRepository.findFollowersByMemberId(memberId);
  }

  public List<FollowResponse> findFollowsByMemberId(Long memberId) {
    return followRepository.findFollowsByMemberId(memberId);
  }
}
