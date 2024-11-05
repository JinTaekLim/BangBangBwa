package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCommentDto {

  @Schema(name = "CreateCommentRequest", description = "댓글 작성 요청 DTO")
  public record Request(
      @Schema(description = "게시글 ID", examples = "1")
      @NotNull(message = "게시글 ID를 입력해주세요. ")
      Long postId,

      @Schema(description = "내용", examples = "content")
      @NotBlank(message = "내용을 입력해주세요.")
      String content
  ){}

  @Schema(name = "CreateCommentResponse", description = "댓글 작성 반환 DTO")
  public record Response(
      @Schema(description = "게시글 ID")
      Long postId,

      @Schema(description = "내용")
      String content
  ){}
}
