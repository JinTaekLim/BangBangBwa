package com.bangbangbwa.backend.domain.member.common.dto;

import com.bangbangbwa.backend.domain.promotion.common.enums.Platform;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class SummaryDto {

  @Schema(name = "SummaryResponse", description = "프로필 요약 조회 응답")
  public record Response(
    @Schema(description = "팔로워 수")
    Long followerCount,
    @Schema(description = "팔로잉 수")
    Long followingCount,
    @Schema(description = "게시글 수")
    Long postCount,
    @Schema(description = "스트리머 여부")
    boolean isStreamer,
    @Schema(description = "스트리머 신청여부")
    boolean isSubmittedToStreamer,
    @Schema(description = "플랫폼 목록")
    List<Platform> platforms
  ) {
  }
}
