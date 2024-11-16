package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class CommentDto {

  @Schema(name = "CommentResponse", description = "댓글 목록 조회 응답")
  public record Response (
    @Schema(description = "댓글 정보 목록")
    List<CommentResponse> comments
  ) {
  }

  @Schema(name = "CommentResponses")
  public record CommentResponse (
      @Schema(description = "게시글 정보")
      CommentResponsePostInfo postInfo,
      @Schema(description = "댓글 정보")
      CommentResponseCommentInfo commentInfo
  ) {
  }

  @Schema(name = "CommentResponsePostInfo")
  public record CommentResponsePostInfo (
      @Schema(description = "게시글 ID")
      Long postId,
      @Schema(description = "제목")
      String title,
      @Schema(description = "작성자 ID")
      Long memberId,
      @Schema(description = "작성자 닉네임")
      String memberName,
      @Schema(description = "작성자 이미지 URL")
      String memberImageUrl,
      @Schema(description = "이미지 포함여부")
      boolean hasImage,
      @Schema(description = "동영상 포함여부")
      boolean hasVideo
  ) {
  }

  @Schema(name = "CommentResponseCommentInfo")
  public record CommentResponseCommentInfo (
      @Schema(description = "댓글 ID")
      Long commentId,
      @Schema(description = "댓글 내용")
      String content,
      @Schema(description = "답글 ID")
      Long replyCommentId,
      @Schema(description = "답글 내용")
      String replyContent
  ) {
  }
}
