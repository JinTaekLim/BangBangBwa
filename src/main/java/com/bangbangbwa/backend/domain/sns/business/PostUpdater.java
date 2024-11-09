package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostStatus;
import org.springframework.stereotype.Component;

@Component
public class PostUpdater {

  public void updateMemberId(Post post, Member member) {
    post.updateMemberId(member.getId());
  }

  public void updateId(Post post, Long postId) { post.updateId(postId);}

  public void updateStatus(Post post, PostStatus status) { post.updatedStatus(status);}
}
