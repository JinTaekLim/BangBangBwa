package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.PostVisibilityMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostVisibilityMemberGenerator {

  // note. 로직 위치 변경 필요
  public List<PostVisibilityMember> generate(Long postId, List<Long> memberList) {
    return memberList.stream()
        .map(memberId -> PostVisibilityMember.builder()
            .createdId("test")
            .memberId(memberId)
            .postId(postId)
            .build())
        .toList();
  }
}
