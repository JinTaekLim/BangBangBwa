package com.bangbangbwa.backend.domain.member.common.dto;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import com.bangbangbwa.backend.domain.streamer.common.enums.PendingType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SummaryDto {

  private Long memberId;
  private Long currentMemberId;
  private Long followerCount;
  @Setter
  private Long followingCount;
  private Long postCount;
  private Boolean isStreamer;
  @Setter
  private Boolean isSubmittedToStreamer;
  private String pendingType;
  @Setter
  private List<PlatformDto> platforms;

  public SummaryDto(Long memberId, Long currentMemberId) {
    this.memberId = memberId;
    this.currentMemberId = currentMemberId;
    this.pendingType = PendingType.PENDING.name();
  }

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
      List<PlatformDto> platforms
  ) {

  }
}
