package com.bangbangbwa.backend.domain.promotion.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Set;

public class PromotionStreamerDto {

  @Schema(name = "PromotionStreamerResponse", description = "홍보 스트리머 응답")
  public record Response(
      @Schema(description = "스트리머 목록")
      Set<PromotionStreamerResponseStreamer> streamerList
  ) {

  }

  public record PromotionStreamerResponseStreamer(
      @Schema(description = "스트리머 오늘의 한마디", example = "한마디")
      String todayComment,
      @Schema(description = "스트리머 자기소개", example = "안녕하세요 이번에 새로 시작하게된 스트리머 이수연 입니다.")
      String selfIntroduction,
      @Schema(description = "스트리머 사진 URL", example = "이미지 URL")
      String imageUrl,
      @Schema(description = "스트리머 이름", example = "이수연")
      String name,
      @Schema(description = "스트리머 관심분야 목록")
      List<PromotionStreamerResponseStreamerInterested> tagList,
      @Schema(description = "스트리머 플랫폼 목록")
      List<PromotionStreamerResponseStreamerPlatform> platformList
  ) {

  }

  public record PromotionStreamerResponseStreamerInterested(
      @Schema(description = "관심 여부", example = "true")
      boolean isInterested,
      @Schema(description = "관심분야 이름", example = "음악")
      String name
  ) {

  }

  public record PromotionStreamerResponseStreamerPlatform(
      @Schema(description = "플랫폼 종류", allowableValues = {"SOOP", "YOUTUBE", "CHZZK"})
      String name,
      @Schema(description = "플랫폼 로고 사진 URL", example = "플랫폼 로고 사진 URL")
      String imageUrl,
      @Schema(description = "플랫폼 프로필 URL", example = "플랫폼 프로필 URL")
      String profileUrl
  ) {

  }
}
