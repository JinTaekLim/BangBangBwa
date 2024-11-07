package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamerInterested;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.util.ArrayList;
import java.util.List;

public class StreamerInterestedListMapper {

  public List<PromotionStreamerResponseStreamerInterested> asDtoList(
      List<Tag> tags) {
    List<PromotionStreamerResponseStreamerInterested> returnList = new ArrayList<>();
    for (Tag tag : tags) {
      PromotionStreamerResponseStreamerInterested dto
          = new PromotionStreamerResponseStreamerInterested(
          checkInterestedIn(), // TODO : 회원 관심 분야 기능 추가 후 수정 예정
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
