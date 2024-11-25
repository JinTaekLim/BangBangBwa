package com.bangbangbwa.backend.domain.promotion.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("platform")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Platform {

  private Long id;
  private String name;
  private String imageUrl;
  private String profileUrl;

  @Builder
  public Platform(
      String name,
      String imageUrl
  ) {
    this.name = name;
    this.imageUrl = imageUrl;
  }
}