package com.bangbangbwa.backend.domain.admin.service;

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

  public PendingStreamer approveStreamer(ApproveStreamerDto.Request request) {
    Admin admin = new Admin(1L); // note : 실제 관리자 정보를 가져오는 로직 필요
    PendingStreamer pendingStreamer = pendingStreamerGenerator.updateAdminId(request, admin);
    pendingStreamerUpdater.updateAdminId(pendingStreamer);
    return pendingStreamer;
  }

}
