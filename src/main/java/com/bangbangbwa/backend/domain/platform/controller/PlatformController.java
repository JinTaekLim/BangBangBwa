package com.bangbangbwa.backend.domain.platform.controller;

import com.bangbangbwa.backend.domain.platform.common.dto.PlatformDto;
import com.bangbangbwa.backend.domain.platform.common.dto.PlatformListDto;
import com.bangbangbwa.backend.domain.platform.service.PlatformService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/platforms")
@RequiredArgsConstructor
public class PlatformController {

  private final PlatformService platformService;

  @PreAuthorize("permitAll()")
  @GetMapping
  public ApiResponse<PlatformListDto.Response> platformList() {
    List<PlatformDto> platformList = platformService.getAllPlatforms();
    PlatformListDto.Response response = new PlatformListDto.Response(platformList);
    return ApiResponse.ok(response);
  }
}
