package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.repository.PostMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMediaCreator {

  private final PostMediaRepository postMediaRepository;

  public void save(String url, Member member) {
    postMediaRepository.save(url, member.getId(), 24);
  }

}
