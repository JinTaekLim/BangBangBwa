package com.bangbangbwa.backend.domain.promotion.service;

import com.bangbangbwa.backend.domain.promotion.business.BannerReader;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto.PromotionBanner;
import com.bangbangbwa.backend.domain.promotion.common.entity.Banner;
import com.bangbangbwa.backend.domain.promotion.common.mapper.BannerMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

  private final BannerReader bannerReader;

  public List<PromotionBanner> getBanners() {
    List<Banner> banners = bannerReader.findAll();
    return BannerMapper.INSTANCE.entityToDto(banners);
  }
}
