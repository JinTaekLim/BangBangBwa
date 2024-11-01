package com.bangbangbwa.backend.domain.promotion.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("streamer_interested")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamerInterested {

  private Long id;
  private Long streamerId;
  private boolean isInterested;
  private String name;

  public StreamerInterested(
      String name
  ) {
    this.name = name;

    this.isInterested = false;
  }

  public void interested() {
    this.isInterested = true;
  }

  public void updatedParent(Long streamerId) {
    this.streamerId = streamerId;
  }
}
