package com.bangbangbwa.backend.domain.promotion.common.vo;

import java.util.List;
import lombok.Getter;

@Getter
public class StreamerVo {

  private Long id;
  private String todayComment;
  private String selfIntroduction;
  private String imageUrl;
  private String name;
  private List<StreamerInterestedVo> interestedList;
  private List<StreamerPlatformVo> platformList;
}
