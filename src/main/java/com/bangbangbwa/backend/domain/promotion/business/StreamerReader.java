package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerVo;
import com.bangbangbwa.backend.domain.promotion.repository.StreamerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamerReader {

  private final StreamerRepository streamerRepository;

  public List<StreamerVo> readAllStreamers() {
    return streamerRepository.findAll();
  }
}
