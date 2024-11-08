package com.bangbangbwa.backend.domain.streamer.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("streamer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Streamer {

  private Long id;
  private Long memberId;

//  private List<String> platforms;  플랫폼 리스트, 이후 작업 필

}
