package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.exception.NotFoundFollowException;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowDeleter {

  private final FollowRepository followRepository;

  public void deleteByFollowerIdAndFollowId(Long followerId, Long followeeId) {
    int status = followRepository.deleteByFollowerIdAndFollowId(followerId, followeeId);
    if (status == 0) {throw new NotFoundFollowException(); }
  }

}
