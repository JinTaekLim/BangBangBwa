package com.bangbangbwa.backend.domain.platform.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class PlatformListDto {

  @Schema(name = "PlatformListResponse", description = "플랫폼 목록 응답 DTO")
  public record Response(
      @Schema(description = "플랫폼 목록")
      List<PlatformDto> platforms
  ) {

  }
}
