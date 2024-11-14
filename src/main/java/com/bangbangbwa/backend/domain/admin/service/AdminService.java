package com.bangbangbwa.backend.domain.admin.service;

import com.bangbangbwa.backend.domain.admin.business.AdminProvider;
import com.bangbangbwa.backend.domain.admin.common.dto.ApproveStreamerDto;
import com.bangbangbwa.backend.domain.admin.common.entity.Admin;
import com.bangbangbwa.backend.domain.streamer.common.business.PendingStreamerGenerator;
import com.bangbangbwa.backend.domain.streamer.common.business.PendingStreamerUpdater;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final PendingStreamerUpdater pendingStreamerUpdater;
  private final PendingStreamerGenerator pendingStreamerGenerator;
  private final AdminProvider adminProvider;

  public PendingStreamer updatePendingStatus(ApproveStreamerDto.Request request) {
    Admin admin = adminProvider.getCurrentAdmin();
    PendingStreamer pendingStreamer = pendingStreamerGenerator.updateAdminId(request, admin);
    pendingStreamerUpdater.updateAdminId(pendingStreamer);
    return pendingStreamer;
  }

}
