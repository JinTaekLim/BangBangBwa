package com.bangbangbwa.backend.domain.promotion.service;

import com.bangbangbwa.backend.domain.promotion.business.RandomStreamerProvider;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamerService {

  private final RandomStreamerProvider randomStreamerProvider;

  public List<PromotionStreamerDto.PromotionStreamerResponseStreamer> getRandomStreamers() {
    return randomStreamerProvider.getStreamers();
  }
}
