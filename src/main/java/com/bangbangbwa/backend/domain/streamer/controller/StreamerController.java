package com.bangbangbwa.backend.domain.streamer.controller;

import com.bangbangbwa.backend.domain.sns.service.SnsService;
import com.bangbangbwa.backend.domain.streamer.common.dto.CreateDailyMessageDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.DailyMessage;
import com.bangbangbwa.backend.domain.streamer.common.mapper.DailyMessageMapper;
import com.bangbangbwa.backend.domain.streamer.service.DailyMessageService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/streamer")
@RequiredArgsConstructor
public class StreamerController {

  private final DailyMessageService dailyMessageService;


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
}
