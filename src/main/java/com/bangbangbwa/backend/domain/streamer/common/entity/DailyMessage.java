package com.bangbangbwa.backend.domain.streamer.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMessage {

  private Long memberId;
  private String message;
  private Long expirationTime;

  @Builder
  public DailyMessage(Long memberId, String message) {
    this.memberId = memberId;
    this.message = message;
    this.expirationTime = 24L;
  }

  public void updateMemberId(Long streamerId) {
    this.memberId = streamerId;
  }
}
