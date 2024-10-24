package com.bangbangbwa.backend.domain.promotion.controller;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto.PromotionBannerResponseBanner;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController implements PromotionApi {

  @GetMapping("/banners")
  public ApiResponse<PromotionBannerDto.Response> getBanners() {
    List<PromotionBannerResponseBanner> banners = new ArrayList<>();
    banners.add(
        new PromotionBannerDto.PromotionBannerResponseBanner(
            "https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            "#FFFFFF"
        ));
    banners.add(
        new PromotionBannerDto.PromotionBannerResponseBanner(
            "https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png",
            "#000000"
        ));
    banners.add(
        new PromotionBannerDto.PromotionBannerResponseBanner(
            "https://corp-homepage-phinf.pstatic.net/MjAyNDEwMjJfMzkg/MDAxNzI5NTYwNTkxNDU4.D12Vl55dLYFTuVFDahpRJuBM-zbCGGqm3g1JGyOI2isg.pdr_g8xDa4ASnDnQ9or7eoXJZv7oUOkfrfGvd0vmg_Mg.PNG/1_NAVER_%282%29.png",
            "#2DB400"
        ));
    PromotionBannerDto.Response response = new PromotionBannerDto.Response(banners);

    return ApiResponse.ok(response);
  }
}
