package com.bangbangbwa.backend.domain.platform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StreamerPlatformDto {

  private Long streamerId;
  private Long platformId;
  private String platformProfileUrl;
}
