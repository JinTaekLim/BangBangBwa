package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamerPlatform;
import com.bangbangbwa.backend.domain.promotion.common.entity.Platform;
import java.util.ArrayList;
import java.util.List;

public class StreamerPlatformListMapper {

  public List<PromotionStreamerResponseStreamerPlatform> asDtoList(
      List<Platform> platforms) {
    List<PromotionStreamerResponseStreamerPlatform> returnList = new ArrayList<>();
    for (Platform platform : platforms) {
      PromotionStreamerResponseStreamerPlatform dto
          = new PromotionStreamerResponseStreamerPlatform(
          platform.getName(),
          platform.getImageUrl(),
          platform.getProfileUrl()
      );
      returnList.add(dto);
    }
    return returnList;
  }
}
