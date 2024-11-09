package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import com.bangbangbwa.backend.global.util.S3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class PostMediaUpdater {

  private final S3Manager s3Manager;

  public void updateUrl(PostMedia postMedia, Post post, MultipartFile file, Member member) {
    String url = s3Manager.upload(file);
    String memberId = String.valueOf(member.getId());

    postMedia.updateUrl(post.getId(), url, memberId);
  }

}
