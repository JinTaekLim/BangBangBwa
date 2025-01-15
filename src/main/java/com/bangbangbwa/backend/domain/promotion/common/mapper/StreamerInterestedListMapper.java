package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerInterested;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.util.ArrayList;
import java.util.List;

public class StreamerInterestedListMapper {

  public List<PromotionStreamerInterested> asDtoList(
      List<Tag> tags
  ) {
    List<PromotionStreamerInterested> returnList = new ArrayList<>();
    for (Tag tag : tags) {
      PromotionStreamerInterested dto
          = new PromotionStreamerInterested(
          checkInterestedIn(),
          tag.getName()
      );
      returnList.add(dto);
    }
    return returnList;
  }

  private boolean checkInterestedIn() {
    return false;
  }
}
