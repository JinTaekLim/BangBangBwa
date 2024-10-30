package com.bangbangbwa.backend.domain.promotion.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("banner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Banner {

  private Long id;
  private String url;
  private String bgColor;

  @Builder
  public Banner(
      String url,
      String bgColor
  ) {
    this.url = url;
    this.bgColor = bgColor;
  }
}
