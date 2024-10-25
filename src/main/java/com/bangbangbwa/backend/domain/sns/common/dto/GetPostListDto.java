package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class GetPostListDto {

  @Schema(name = "GetPostListDto", description = "게시글 목록 조회 반환")
  public record Response(
      @Schema(description = "게시물ID")
      Long postId,
      @Schema(description = "제목")
      String title
  ) {}

}
