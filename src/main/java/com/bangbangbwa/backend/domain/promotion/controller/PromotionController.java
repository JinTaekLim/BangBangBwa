package com.bangbangbwa.backend.domain.promotion.controller;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamer;
import com.bangbangbwa.backend.domain.promotion.service.BannerService;
import com.bangbangbwa.backend.domain.promotion.service.StreamerService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController implements PromotionApi {

  private final BannerService bannerService;
  private final StreamerService streamerService;

  @PreAuthorize("permitAll()")
  @GetMapping("/banners")
  public ApiResponse<PromotionBannerDto.Response> getBanners() {
    List<PromotionBannerDto.PromotionBanner> banners = bannerService.getBanners();
    PromotionBannerDto.Response response = new PromotionBannerDto.Response(banners);

    return ApiResponse.ok(response);
  }

  @PreAuthorize("permitAll()")
  @GetMapping("/streamers")
  public ApiResponse<PromotionStreamerDto.Response> getStreamers() {
    Set<PromotionStreamer> streamerList = streamerService.getRandomStreamers();
    PromotionStreamerDto.Response response = new PromotionStreamerDto.Response(streamerList);
    return ApiResponse.ok(response);
  }
}
