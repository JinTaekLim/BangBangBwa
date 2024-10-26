package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;

public class MemberNicknameDto {

  @Schema(name = "NicknamesResponse", description = "랜덤 닉네임 제공 응답 DTO")
  public record Response(
      @Schema(description = "랜덤 닉네임", examples = "차가운하마, 뜨거운감자, 행복한고구마")
      Set<String> nicknames
  ) {

  }
}
