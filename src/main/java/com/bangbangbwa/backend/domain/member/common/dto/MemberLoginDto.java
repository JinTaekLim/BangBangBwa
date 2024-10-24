package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class MemberLoginDto {

  @Schema(name = "LoginRequest", description = "로그인 요청 DTO")
  public record Request(

      @Schema(description = "인가코드")
      @NotBlank(message = "인가 코드를 입력헤주세요.")
      String authCode
  ) {

  }
}
