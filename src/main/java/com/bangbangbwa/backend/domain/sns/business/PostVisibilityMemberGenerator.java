package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.entity.PostVisibilityMember;
import com.bangbangbwa.backend.domain.sns.common.enums.VisibilityType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostVisibilityMemberGenerator {

  public List<PostVisibilityMember> generate(
      Post post,
      VisibilityType type,
      List<Long> memberList
  ) {
    return generateVisibilityMembers(post, memberList, type);
  }


  private List<PostVisibilityMember> generateVisibilityMembers(Post post, List<Long> memberList, VisibilityType type) {
    return memberList.stream()
        .map(memberId -> PostVisibilityMember.builder()
            .createdId(post.getMemberId().toString())
            .type(type)
            .memberId(memberId)
            .postId(post.getId())
            .build())
        .toList();
  }
}
