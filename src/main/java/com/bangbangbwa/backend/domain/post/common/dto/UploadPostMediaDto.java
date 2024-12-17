package com.bangbangbwa.backend.domain.post.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class UploadPostMediaDto {

  @Schema(name = "UploadPostMediaResponse", description = "게시글 미디어 업로드 반환 DTO")
  public record Response(
      @Schema(description = "미디어 URL")
      String url
  ) {

  }


}
