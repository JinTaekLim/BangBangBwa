package com.bangbangbwa.backend.domain.member.common.dto;

import lombok.Getter;

@Getter
public class PostDto {
  private Long memberId;
  private Long postId;
  private boolean isPinned;
  private String title;
  private String content;
  private String createdDate;
  private boolean hasImage;
  private boolean hasVideo;
}
