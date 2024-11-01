package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamerPlatform;
import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerPlatformVo;
import java.util.ArrayList;
import java.util.List;

public class StreamerPlatformListMapper {

  public List<PromotionStreamerResponseStreamerPlatform> asDtoList(
      List<StreamerPlatformVo> voList) {
    List<PromotionStreamerResponseStreamerPlatform> returnLit = new ArrayList<>();
    for (StreamerPlatformVo vo : voList) {
      PromotionStreamerResponseStreamerPlatform dto
          = new PromotionStreamerResponseStreamerPlatform(
          vo.getName(),
          vo.getImageUrl(),
          vo.getProfileUrl()
      );
      returnLit.add(dto);
    }
    return returnLit;
  }
}
