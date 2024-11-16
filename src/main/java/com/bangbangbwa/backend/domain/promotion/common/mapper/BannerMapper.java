package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto;
import com.bangbangbwa.backend.domain.promotion.common.entity.Banner;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BannerMapper {

  BannerMapper INSTANCE = Mappers.getMapper(BannerMapper.class);

  @Mapping(target = "url", source = "banners.url")
  @Mapping(target = "bgColor", source = "banners.bgColor")
  List<PromotionBannerDto.PromotionBanner> entityToDto(List<Banner> banners);
}
