package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class FollowerDto {

  @Schema(name = "FollowerResponse", description = "팔로워 목록 조회 응답")
  public record Response(
    @Schema(description = "팔로워 정보 목록")
    List<FollowerResponse> followers
  ) {
  }

  @Schema(name = "FollowerResponses")
  public record FollowerResponse(
      @Schema(description = "팔로워 사용자 ID")
      Long memberId,
      @Schema(description = "팔로워 닉네임")
      String nickname,
      @Schema(description = "팔로워 이미지 URL")
      String profile
  ) {
  }
}
