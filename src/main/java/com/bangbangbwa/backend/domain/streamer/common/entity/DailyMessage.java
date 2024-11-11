package com.bangbangbwa.backend.domain.streamer.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMessage {

  private Long streamerId;
  private String message;
  private Long expirationTime;

  @Builder
  public DailyMessage(Long streamerId, String message) {
    this.streamerId = streamerId;
    this.message = message;
    this.expirationTime = 24L;
  }

  public void updateStreamerId(Long streamerId) {
    this.streamerId = streamerId;
  }
}
