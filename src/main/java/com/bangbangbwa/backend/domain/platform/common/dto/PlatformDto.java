package com.bangbangbwa.backend.domain.platform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlatformDto {

  private Long id;
  private String name;
  private String imageUrl;
  private String profileUrl;
}
