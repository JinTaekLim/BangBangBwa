package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class UploadPostMediaDto {

  @Schema(name = "UploadPostMediaRequest", description = "게시글 미디어 업로드 요청 DTO")
  public record Request(
      @Schema(description = "게시물 ID", examples = "1 (전달 받은 게시물 번호가 없을 경우 미입력)")
      Long postId
  ){}

  @Schema(name = "UploadPostMediaResponse", description = "게시글 미디어 업로드 반환 DTO")
  public record Response(
      @Schema(description = "게시물 ID")
      Long postId,
      @Schema(description = "미디어 URL")
      String url
  ){}


}
