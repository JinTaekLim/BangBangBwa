package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {

  private final CommentRepository commentRepository;

  public Comment findByPostAndMember(Post post, Member member) {
    return commentRepository.findByPostAndMember(post.getId(), member.getId())
        .orElse(null);
  }
}
