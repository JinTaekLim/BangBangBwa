package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerPlatform;
import com.bangbangbwa.backend.domain.promotion.common.entity.Platform;
import java.util.ArrayList;
import java.util.List;

public class StreamerPlatformListMapper {

  public List<PromotionStreamerPlatform> asDtoList(
      List<Platform> platforms) {
    List<PromotionStreamerPlatform> returnList = new ArrayList<>();
    for (Platform platform : platforms) {
      PromotionStreamerPlatform dto
          = new PromotionStreamerPlatform(
          platform.getName(),
          platform.getImageUrl(),
          platform.getProfileUrl()
      );
      returnList.add(dto);
    }
    return returnList;
  }
}
