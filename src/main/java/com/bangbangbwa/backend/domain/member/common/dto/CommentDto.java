package com.bangbangbwa.backend.domain.member.common.dto;

public class CommentDto {
  public record Response (
    CommentResponsePostInfo postInfo,
    CommentResponseCommentInfo commentInfo
  ) {
  }

  public record CommentResponsePostInfo (
      Long postId,
      String title,
      Long memberId,
      String memberName,
      String memberImageUrl,
      boolean hasImage,
      boolean hasVideo
  ) {
  }

  public record CommentResponseCommentInfo (
      Long commentId,
      String content,
      Long replyCommentId,
      String replyContent
  ) {
  }
}
