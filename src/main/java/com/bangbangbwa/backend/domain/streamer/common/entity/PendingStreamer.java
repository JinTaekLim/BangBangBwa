package com.bangbangbwa.backend.domain.streamer.common.entity;

import com.bangbangbwa.backend.domain.streamer.common.enums.PendingType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("pendingStreamer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PendingStreamer {

  private final String SELF = "SELF";

  private Long id;
  private Long memberId;
  private Long adminId;
  private String platformUrl;
  private PendingType status;
  private String createdId;
  private LocalDateTime createdAt;
  private String updatedId;
  private LocalDateTime updatedAt;

  @Builder
  public PendingStreamer(
      Long id,
      Long memberId,
      Long adminId,
      String platformUrl,
      PendingType status
  ) {
    this.id = id;
    this.memberId = memberId;
    this.adminId = adminId;
    this.platformUrl = platformUrl;
    this.status = (status != null) ? status : PendingType.PENDING;
    this.createdId = SELF;
    this.createdAt = LocalDateTime.now();
  }

  public void updateMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void updateAdminId(Long adminId) {
    this.adminId = adminId;
    this.updatedId = adminId.toString();
    this.updatedAt = LocalDateTime.now();
  }
}
