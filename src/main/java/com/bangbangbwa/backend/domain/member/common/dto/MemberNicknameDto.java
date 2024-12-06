package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class MemberNicknameDto {

  @Schema(name = "NicknamesResponse", description = "랜덤 닉네임 제공 응답 DTO")
  public record Response(
      @Schema(description = "랜덤 닉네임", example = "차가운하마_123")
      String nickname
  ) {

  }
}
