package com.bangbangbwa.backend.domain.platform.business;

import com.bangbangbwa.backend.domain.platform.exception.NonStreamerPlatformRelation;
import com.bangbangbwa.backend.domain.platform.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformValidator {

  private final PlatformRepository platformRepository;

  public void validateStreamerPlatform(Long streamerId, Long platformId) {
    if (!platformRepository.isExistsStreamerPlatform(streamerId, platformId)) {
      throw new NonStreamerPlatformRelation();
    }
  }
}
