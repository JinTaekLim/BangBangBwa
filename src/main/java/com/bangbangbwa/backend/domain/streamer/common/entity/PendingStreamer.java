package com.bangbangbwa.backend.domain.streamer.common.entity;

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
  private Long member_id;
  private Long admin_id;
  private String platformUrl;
  private String createdId;
  private LocalDateTime createdAt;
  private String updatedId;
  private LocalDateTime updatedAt;

  @Builder
  public PendingStreamer(Long member_id, Long admin_id, String platformUrl) {
    this.member_id = member_id;
    this.admin_id = admin_id;
    this.platformUrl = platformUrl;
    this.createdId = SELF;
    this.createdAt = LocalDateTime.now();
  }

  public void updateMemberId(Long memberId) {
    this.member_id = memberId;
  }
}
