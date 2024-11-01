package com.bangbangbwa.backend.domain.promotion.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("streamer_platform")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamerPlatform {

  private Long id;
  private Long streamerId;
  private String name;
  private String imageUrl;
  private String profileUrl;

  public StreamerPlatform(
      String name,
      String imageUrl,
      String profileUrl
  ) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.profileUrl = profileUrl;
  }

  public void updateParent(Long streamerId) {
    this.streamerId = streamerId;
  }
}
