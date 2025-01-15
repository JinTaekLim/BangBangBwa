package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public class MemberSignupDto {

  @Schema(name = "SignupRequest", description = "회원가입 요청 DTO")
  public record Request(

      @Schema(description = "이용 약관 동의", example = "true")
      @AssertTrue(message = "이용 약관 동의 여부 확인 바랍니다.")
      boolean usageAgree,

      @Schema(description = "개인 정보 수집 및 저장 동의", example = "true")
      @AssertTrue(message = "개인 정보 수집 및 저장 동의 여부 확인 바랍니다.")
      boolean personalAgree,

      @Schema(description = "회원 탈퇴 시 처리 방안", example = "true")
      @AssertTrue(message = "회원 탈퇴 시 처리 방안 동의 여부 확인 바랍니다.")
      boolean withdrawalAgree,

      @Schema(description = "OAuth 토큰", example = "AV)$M@LC>D>GM12)c(faEF")
      @NotBlank(message = "oauth 토큰을 입력해주세요.")
      String oauthToken,

      @Schema(description = "닉네임", example = "nick")
      @NotBlank(message = "닉네임을 입력해주세요.")
      @Size(max = 14, message = "최대 {max}자 이하로 입력해주세요.")
      @Pattern(regexp = "^[A-Za-z가-힣0-9()\\-_]+$", message = "한글,영문,숫자, 특수문자('(',')','-','_')만 사용 가능합니다.")
      String nickname,

      @Schema(description = "관심 분야", example = "[\"국내여행\", \"해외여행\", \"마인크래프트\"]")
      List<String> tags
  ) {

  }

  @Schema(name = "SignupResponse", description = "회원가입 응답 DTO")
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
