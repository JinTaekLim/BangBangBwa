package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamerInterested;
import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerInterestedVo;
import java.util.ArrayList;
import java.util.List;

public class StreamerInterestedListMapper {

  public List<PromotionStreamerResponseStreamerInterested> asDtoList(
      List<StreamerInterestedVo> voList) {
    List<PromotionStreamerResponseStreamerInterested> returnLit = new ArrayList<>();
    for (StreamerInterestedVo vo : voList) {
      PromotionStreamerResponseStreamerInterested dto
          = new PromotionStreamerResponseStreamerInterested(
          false, // TODO : 회원 관심 분야 기능 추가 후 수정 예정
          vo.getName()
      );
      returnLit.add(dto);
    }
    return returnLit;
  }
}
