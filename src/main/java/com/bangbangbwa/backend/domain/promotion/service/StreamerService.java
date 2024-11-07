package com.bangbangbwa.backend.domain.promotion.service;

import com.bangbangbwa.backend.domain.promotion.business.RandomStreamerProvider;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamerService {

  private final RandomStreamerProvider randomStreamerProvider;

  public Set<PromotionStreamerResponseStreamer> getRandomStreamers() {
    return randomStreamerProvider.getStreamers();
  }
}
