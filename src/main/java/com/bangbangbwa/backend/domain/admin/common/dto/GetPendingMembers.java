package com.bangbangbwa.backend.domain.admin.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class GetPendingMembers {

  @Schema(name = "GetPendingMembersResponse", description = "승인 요청 대기 유저 반환 DTO")
  public record Response(
      @Schema(description = "승인 요청 대기 유저 목록")
      List<GetPendingMemberResponse> pendingMembers
  ) {}

  @Schema(name = "GetPendingMemberResponse")
  public record GetPendingMemberResponse(
      @Schema(description = "멤버 ID")
      Long memberId,
      @Schema(description = "방송국 URL")
      String platformUrl,
      @Schema(description = "신청 날짜")
      LocalDateTime registrationDate,
      @Schema(description = "프로필 사진")
      String profile,
      @Schema(description = "닉네임")
      String nickname
  ) { }
}
