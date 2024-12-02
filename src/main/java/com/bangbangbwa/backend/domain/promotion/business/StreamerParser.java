package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamer;
import com.bangbangbwa.backend.domain.promotion.common.dto.StreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.mapper.StreamerMapper;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class StreamerParser {

  public Set<PromotionStreamer> voToResponse(Set<StreamerDto> streamerList) {
    return StreamerMapper.INSTANCE.entitiesToResponses(streamerList);
  }
}
