package com.bangbangbwa.backend.domain.platform.business;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import com.bangbangbwa.backend.domain.platform.repository.PlatformRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformReader {

  private final PlatformRepository platformRepository;

  public List<PlatformDto> getAllPlatforms() {
    return platformRepository.findAll();
  }
}
