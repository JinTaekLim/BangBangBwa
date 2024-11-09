package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class PostMediaGenerator {

  private final PostMediaUpdater postMediaUpdater;

  public PostMedia generate(Post post, MultipartFile file, Member member) {
    PostMedia postMedia = PostMedia.builder().build();
    postMediaUpdater.updateUrl(postMedia, post, file, member);
    return postMedia;
  }

}
