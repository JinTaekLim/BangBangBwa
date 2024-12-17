package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.post.common.entity.PostVisibilityMember;
import com.bangbangbwa.backend.domain.sns.repository.PostVisibilityMemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostVisibilityMemberCreator {

  private final PostVisibilityMemberRepository postVisibilityMemberRepository;

  public void saveList(List<PostVisibilityMember> postVisibilityMemberList) {
    postVisibilityMemberRepository.saveList(postVisibilityMemberList);
  }
}
