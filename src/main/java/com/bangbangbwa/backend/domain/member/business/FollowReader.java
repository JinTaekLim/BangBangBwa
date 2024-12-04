package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowReader {

  private final FollowRepository followerRepository;

  public List<FollowDto> findAllFollowers(Long memberId) {
    return followerRepository.findAllFollowers(memberId);
  }
}
