package com.bangbangbwa.backend.domain.member.common.dto;

import lombok.Getter;

@Getter
public class CommentDto {
  // Post Info
  private Long postId;
  private Long memberId;
  private String creatorName;
  private String creatorImage;
  private String title;
  private boolean hasImage;
  private boolean hasVideo;

  // Comment Info
  private CommentInfo commentInfo;

  @Getter
  public static class CommentInfo {
    private Long memberId;
    private Long commentId;
    private String content;
    private String replyContent;
  }
}
