package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostUpdater {

  public void updateMemberId(Post post, Member member) {
    post.updateMemberId(member.getId());
  }
}
