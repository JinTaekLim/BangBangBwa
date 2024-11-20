package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {

  private final CommentRepository commentRepository;

  public Comment findByPost(Post post) {
     return commentRepository.findByPostId(post.getId()).orElse(null);
  }
}
