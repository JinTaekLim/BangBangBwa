package com.bangbangbwa.backend.domain.streamer.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddPlatformDto {

  @Schema(name = "AddPlatformRequest", description = "플랫폼 추가 요청 DTO")
  public record Request(
      @Schema(description = "플랫폼 아이디", example = "1")
      @NotNull(message = "플랫폼 아이디를 입력 바랍니다.")
      Long platformId,
      @Schema(description = "플랫폼 프로필 링크", example = "https://www.youtube/xx/xxx")
      @NotNull(message = "플랫폼 프로필 링크를 입력 바랍니다.")
      String platformProfileUrl
  ) {

  }
}
