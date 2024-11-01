package com.bangbangbwa.backend.domain.promotion.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("streamer")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Streamer {

  private Long id;
  private String todayComment;
  private String selfIntroduction;
  private String imageUrl;
  private String name;
}
