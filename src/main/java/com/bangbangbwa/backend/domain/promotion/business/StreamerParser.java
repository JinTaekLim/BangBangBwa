package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamer;
import com.bangbangbwa.backend.domain.promotion.common.dto.StreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.mapper.StreamerMapper;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class StreamerParser {

  public Set<PromotionStreamer> voToResponse(
      Set<StreamerDto> streamerList,
      List<TagDto> interestedTagList
  ) {
    return StreamerMapper.INSTANCE.entitiesToResponses(streamerList, interestedTagList);
  }
}
