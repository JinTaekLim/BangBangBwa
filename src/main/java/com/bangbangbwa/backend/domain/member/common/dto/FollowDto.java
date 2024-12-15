package com.bangbangbwa.backend.domain.member.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class FollowDto {

  @Schema(name = "FollowerResponse", description = "팔로우 목록 조회 응답")
  public record Response(
    @Schema(description = "팔로우 정보 목록")
    List<FollowResponse> follows
  ) {
  }

  @Schema(name = "FollowerResponses")
  public record FollowResponse(
      @Schema(description = "팔로우 사용자 ID")
      Long memberId,
      @Schema(description = "팔로우 닉네임")
      String nickname,
      @Schema(description = "팔로우 이미지 URL")
      String profile
  ) {
  }
}
