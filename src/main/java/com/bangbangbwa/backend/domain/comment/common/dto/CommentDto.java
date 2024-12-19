package com.bangbangbwa.backend.domain.comment.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {

  private Long id;
  private Long postId;
  private Long memberId;
  private String replyComment;
}
