package com.bangbangbwa.backend.domain.post.business;

import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDeleter {

  private final PostRepository postRepository;

  public void deleteByWithdraw(Long memberId) {
    postRepository.deleteByWithdraw(memberId);
  }
}
