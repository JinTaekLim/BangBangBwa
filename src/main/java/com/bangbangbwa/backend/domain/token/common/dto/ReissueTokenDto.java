package com.bangbangbwa.backend.domain.token.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReissueTokenDto {

  @Schema(name = "ReissueToken", description = "토큰 재발급 DTO")
  public record Response(
      @Schema(description = "액세스 토큰", example = "xxx.xxx.xxx")
      String accessToken,
      @Schema(description = "리프레쉬 토큰", example = "xxx.xxx.xxx")
      String refreshToken,
      @Schema(description = "회원 아이디", example = "1")
      Long memberId,
      @Schema(description = "회원 권한", example = "MEMBER")
      String role,
      @Schema(description = "회원 프로필 이미지", example = "https://www.xxxx.xxxx.xxx")
      String profileImage
  ) {

  }
}
