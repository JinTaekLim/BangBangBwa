package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import com.bangbangbwa.backend.domain.sns.repository.PostMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMediaCreator {

  private final PostMediaRepository postMediaRepository;

  public void save(PostMedia postMedia) {
    postMediaRepository.save(postMedia);
  }

}
