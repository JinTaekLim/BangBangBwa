package com.bangbangbwa.backend.domain.comment.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyCommentDto {

  private MyCommentPostInfo postInfo;
  private MyCommentCommentInfo commentInfo;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MyCommentPostInfo {

    private Long postId;
    private String title;
    private Long memberId;
    private String memberNickname;
    private String memberImageUrl;
    private boolean hasImage;
    private boolean hasVideo;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MyCommentCommentInfo {

    private Long commentId;
    private String content;
    private String replyContent;
  }
}
