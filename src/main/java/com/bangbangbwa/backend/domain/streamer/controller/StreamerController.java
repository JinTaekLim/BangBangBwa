package com.bangbangbwa.backend.domain.streamer.controller;

import com.bangbangbwa.backend.domain.promotion.service.StreamerService;
import com.bangbangbwa.backend.domain.streamer.common.dto.AddPlatformDto;
import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import com.bangbangbwa.backend.domain.streamer.common.mapper.DailyMessageMapper;
import com.bangbangbwa.backend.domain.streamer.service.DailyMessageService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/streamer")
@RequiredArgsConstructor
public class StreamerController {

  private final DailyMessageService dailyMessageService;
  private final StreamerService streamerService;

  @PostMapping("/createDailyMessage")
  @PreAuthorize("hasAuthority('STREAMER')")
  public ApiResponse<CreateDailyMessageDto.Response> createDailyMessage(
      @RequestBody @Valid CreateDailyMessageDto.Request request
  ) {
    DailyMessage dailyMessage = dailyMessageService.createDailyMessage(request);
    CreateDailyMessageDto.Response response = DailyMessageMapper
        .INSTANCE
        .dtoToPromoteStreamerResponse(dailyMessage);

    return ApiResponse.ok(response);
  }

  @PostMapping("/save/platform")
  @PreAuthorize("hasAuthority('STREAMER')")
  public ApiResponse<Null> addStreamerPlatform(
      @RequestBody @Valid AddPlatformDto.Request request
  ) {
    streamerService.addStreamerPlatform(request);
    return ApiResponse.ok();
  }

  @DeleteMapping("/delete/platform/{platformId}")
  @PreAuthorize("hasAuthority('STREAMER')")
  public ApiResponse<Null> removeStreamerPlatform(
      @PathVariable Long platformId
  ) {
    streamerService.removeStreamerPlatform(platformId);
    return ApiResponse.ok();
  }
}
