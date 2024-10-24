package com.bangbangbwa.backend.domain.promotion.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class PromotionStreamerDto {

  @Schema(name = "PromotionStreamerResponse", description = "홍보 스트리머 DTO")
  public record Response(
      @Schema(description = "스트리머 목록")
      List<PromotionStreamerResponseStreamer> streamerList
  ) {}

  public record PromotionStreamerResponseStreamer (
      @Schema(description = "스트리머 사진 URL")
      String imageUrl,
      @Schema(description = "스트리머 프로필 URL")
      String profileUrl,
      @Schema(description = "스트리머 관심분야 목록")
      List<PromotionStreamerResponseStreamerInterested> interestedList,
      @Schema(description = "스트리머 이름")
      String name
  ) {}

  public record PromotionStreamerResponseStreamerInterested (
      @Schema(description = "관심 여부")
      boolean isInterested,
      @Schema(description = "관심분야 이름")
      String name
  ) {}
}
