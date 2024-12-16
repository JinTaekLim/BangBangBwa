package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class TogglePostPinDto {

  @Schema(name = "SignupRequest", description = "게시물 고정 요청 DTO")
  public record Request(
      @Schema(description = "게시물 ID", example = "1L")
      @NotNull(message = "게시물 ID를 입력해주세요.")
      Long postId,
      @Schema(description = "고정 상태", example = "true/false")
      @NotNull(message = "고정 상태를 입력해주세요.")
      boolean pinned
  )
  { }

}
