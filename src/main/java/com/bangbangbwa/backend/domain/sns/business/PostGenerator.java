package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostGenerator {

  private final PostParser postParser;
  private final PostUpdater postUpdater;

  public Post generate(CreatePostDto.Request request, Member member) {
    Post post = postParser.requestToEntity(request);
    postUpdater.updateMemberId(post, member);
    return post;
  }

  public Post generate(Member member) {
    Post post = Post.builder().build();
    postUpdater.updateMemberId(post, member);
    return post;
  }

  public Post update(CreatePostDto.Request request, Long postId) {
    Post post = postParser.requestToEntity(request);
    postUpdater.updateId(post, postId);
    postUpdater.updateStatus(post, PostStatus.PUBLISHED);
    return post;
  }
}
