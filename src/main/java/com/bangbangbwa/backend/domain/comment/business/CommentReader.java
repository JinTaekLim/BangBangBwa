package com.bangbangbwa.backend.domain.comment.business;

import com.bangbangbwa.backend.domain.comment.exception.NotFoundCommentException;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {

  private final CommentRepository commentRepository;

  public Comment findById(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
  }
}
