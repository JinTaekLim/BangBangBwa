package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import com.bangbangbwa.backend.domain.promotion.common.mapper.StreamerMapper;
import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerVo;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StreamerParser {

  public List<PromotionStreamerResponseStreamer> voToResponse(List<StreamerVo> streamerVoList) {
    return StreamerMapper.INSTANCE.voToDto(streamerVoList);
  }
}
