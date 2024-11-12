package com.bangbangbwa.backend.domain.promotion.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class PromotionBannerDto {

  @Schema(name = "PromotionBannerResponse", description = "홍보 배너 응답")
  public record Response(
      @Schema(description = "배너 목록")
      List<PromotionBannerResponseBanner> bannerList
  ) {

  }

  @Schema(description = "홍보 배너 응답 - 배너 목록")
  public record PromotionBannerResponseBanner(
      @Schema(description = "배너 이미지 URL")
      String url,
      @Schema(description = "배너 배경색", example = "#FFFFFFF")
      String bgColor
  ) {

  }
}
