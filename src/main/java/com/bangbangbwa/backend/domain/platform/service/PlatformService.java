package com.bangbangbwa.backend.domain.platform.service;

import com.bangbangbwa.backend.domain.platform.business.PlatformReader;
import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformService {

  private final PlatformReader platformReader;

  public List<PlatformDto> getAllPlatforms() {
    return platformReader.getAllPlatforms();
  }
}
