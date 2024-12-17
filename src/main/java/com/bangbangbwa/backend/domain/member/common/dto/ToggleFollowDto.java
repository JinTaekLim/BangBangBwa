package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ToggleFollowDto {

  @Schema(name = "ToggleFollowRequest", description = "팔로우/언팔로우 요청")
  public record Request(
      @Schema(description = "팔로우 사용자 ID", example = "1L")
      @NotNull(message = "팔로우 사용자 ID를 입력해주세요.")
      Long memberId,
      @Schema(description = "팔로우 여부", example = "true/false")
      @NotNull(message = "팔로우 여부를 입력해주세요.")
      boolean isFollow
  ){}

}
