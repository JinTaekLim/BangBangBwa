package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import org.springframework.stereotype.Component;

@Component
public class FollowUpdater {

  public void updateFollower(Follow follow, Long followerId) {
    follow.updateFollowerId(followerId);
  }
}
