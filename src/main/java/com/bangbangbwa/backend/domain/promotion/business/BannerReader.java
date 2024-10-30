package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.entity.Banner;
import com.bangbangbwa.backend.domain.promotion.repository.BannerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BannerReader {

  private final BannerRepository bannerRepository;

  public List<Banner> findAll() {
    return bannerRepository.findAll();
  }
}
