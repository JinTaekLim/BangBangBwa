package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentUpdater {

  public void updateMemberId(Comment comment, Member member) {
    comment.updateMemberId(member.getId());
  }
}
