package com.bangbangbwa.backend.domain.platform.business;

import com.bangbangbwa.backend.domain.platform.common.dto.StreamerPlatformDto;
import com.bangbangbwa.backend.domain.platform.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformRelation {

  private final PlatformRepository platformRepository;

  public void relation(StreamerPlatformDto dto) {
    platformRepository.saveStreamerPlatform(dto);
  }

  public void breakRelation(StreamerPlatformDto dto) {
    platformRepository.removeStreamerPlatform(dto);
  }
}
