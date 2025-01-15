package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class MemberLoginDto {

  @Schema(name = "LoginRequest", description = "로그인 요청 DTO")
  public record Request(

      @Schema(description = "인가코드", example = "인가코드")
      @NotBlank(message = "인가코드를 입력헤주세요.")
      String authCode
  ) {

  }

  @Schema(name = "LoginResponse", description = "로그인 응답 DTO")
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
