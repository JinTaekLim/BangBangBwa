package com.bangbangbwa.backend.domain.sns.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SearchMemberDto {

  @Schema(name = "SearchMemberResponse", description = "유저 검색 반환")
  public record Response(
      @Schema(description = "멤버ID")
      Long memberId,
      @Schema(description = "닉네임")
      String nickname
  ) {}
}
