package com.bangbangbwa.backend.domain.admin.controller;


import com.bangbangbwa.backend.domain.admin.common.dto.ApproveStreamerDto;
import com.bangbangbwa.backend.domain.admin.service.AdminService;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.common.mapper.PendingStreamerMapper;
import com.bangbangbwa.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  @PostMapping("/approveStreamer")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ApiResponse<ApproveStreamerDto.Response> approveStreamer(
      @RequestBody @Valid ApproveStreamerDto.Request request
  ){
    PendingStreamer pendingStreamer = adminService.approveStreamer(request);
    ApproveStreamerDto.Response response = PendingStreamerMapper
        .INSTANCE
        .dtoToApproveStreamerResponse(pendingStreamer);

    return ApiResponse.ok(response);
  }
}
